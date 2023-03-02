package com.example.Book_My_Show_Application.Service;

import com.example.Book_My_Show_Application.Converters.ShowConverter;
import com.example.Book_My_Show_Application.Entities.*;
import com.example.Book_My_Show_Application.EntryDtos.ShowEntryDto;
import com.example.Book_My_Show_Application.Enums.SeatType;
import com.example.Book_My_Show_Application.Repository.MovieRepository;
import com.example.Book_My_Show_Application.Repository.ShowRepository;
import com.example.Book_My_Show_Application.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService
{
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowEntryDto showEntryDto) throws Exception
    {
        // Validation check
        if(!movieRepository.existsById(showEntryDto.getMovieId()) || !theaterRepository.existsById(showEntryDto.getTheaterId()))
        {
            throw new Exception("Movie or Theater does not exist!");
        }

        //1. Create a showEntity
        ShowEntity showEntity = ShowConverter.convertShowEntryDtoToEntity(showEntryDto);

        int movieId = showEntryDto.getMovieId();
        int theaterId = showEntryDto.getTheaterId();

        MovieEntity movieEntity = movieRepository.findById(movieId).get();
        TheaterEntity theaterEntity = theaterRepository.findById(theaterId).get();

        //Setting the attribute of foreignKe
        showEntity.setMovieEntity(movieEntity);
        showEntity.setTheaterEntity(theaterEntity);

        //Pending attributes are the listOfShowSeatsEntity
        List<ShowSeatEntity> showSeatEntityList = createShowSeatEntity(showEntryDto, showEntity);
        showEntity.setListOfShowSeats(showSeatEntityList);
        // Now show entity is set


        //Now we  also need to update the parent entities(MovieEntity and TheaterEntity)
        showEntity = showRepository.save(showEntity);
        List<ShowEntity> showEntityList = movieEntity.getShowEntityList();  // get
        showEntityList.add(showEntity);  // set
        movieEntity.setShowEntityList(showEntityList);   // again update

        movieRepository.save(movieEntity);   // saved movie(parent) update

        List<ShowEntity> showEntityList1 = theaterEntity.getShowEntityList();
        showEntityList1.add(showEntity);
        theaterEntity.setShowEntityList(showEntityList1);

        theaterRepository.save(theaterEntity);

        // We are not saving anything in showRepository because we already saved showEntity in its parents(Cascading)
        return "The show has been added successfully";
    }

    private List<ShowSeatEntity> createShowSeatEntity(ShowEntryDto showEntryDto, ShowEntity showEntity)
    {
        //Now the goal is to create the ShowSeatEntity
        //And We need to set its attribute

        TheaterEntity theaterEntity = showEntity.getTheaterEntity();   // Since creating show for particular theater
        List<TheaterSeatEntity> theaterSeatEntityList = theaterEntity.getTheaterSeatEntityList();  // Got all seats of particular theater

        List<ShowSeatEntity> showSeatEntityList = new ArrayList<>();

        for(TheaterSeatEntity theaterSeatEntity : theaterSeatEntityList)
        {
            // Create new show seat entity and set its attributes
            ShowSeatEntity showSeatEntity = new ShowSeatEntity();

            showSeatEntity.setSeatNo(theaterSeatEntity.getSeatNo());
            showSeatEntity.setSeatType(theaterSeatEntity.getSeatType());

            // To set price
            if(theaterSeatEntity.getSeatType().equals(SeatType.CLASSIC))
            {
                showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());
            }
            else
            {
                showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());
            }

            showSeatEntity.setBooked(false);
            showSeatEntity.setShowEntity(showEntity);  //parent : foreign key for the showSeat Entity

            showSeatEntityList.add(showSeatEntity);   //Adding it to the list
        }

        return showSeatEntityList;    // No need to save in repository because it is child wrt to showEntity
    }
}
