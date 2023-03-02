package com.example.Book_My_Show_Application.Service;

import com.example.Book_My_Show_Application.Converters.TicketConverter;
import com.example.Book_My_Show_Application.Entities.ShowEntity;
import com.example.Book_My_Show_Application.Entities.ShowSeatEntity;
import com.example.Book_My_Show_Application.Entities.TicketEntity;
import com.example.Book_My_Show_Application.Entities.UserEntity;
import com.example.Book_My_Show_Application.EntryDtos.TicketEntryDto;
import com.example.Book_My_Show_Application.Repository.ShowRepository;
import com.example.Book_My_Show_Application.Repository.TicketRepository;
import com.example.Book_My_Show_Application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService 
{

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;


    public String addTicket(TicketEntryDto ticketEntryDto) throws Exception
    {
        //1. Create TicketEntity from entryDto : Convert DTO ---> Entity
        TicketEntity ticketEntity = TicketConverter.convertTicketEntryDtoToEntity(ticketEntryDto);

        //Validation : Check if the requested seats are available or not ?
        boolean isValidRequest = checkValidityOfRequestedSeats(ticketEntryDto);

        if(isValidRequest==false)
        {
            throw new Exception("Requested seats are not available");
        }
        //Now, We assume that the requestedSeats are valid

        //Calculate the total amount :
        ShowEntity showEntity = showRepository.findById(ticketEntryDto.getShowId()).get();
        List<ShowSeatEntity> seatEntityList = showEntity.getListOfShowSeats();
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        int totalAmount = 0;
        for(ShowSeatEntity showSeatEntity : seatEntityList)
        {
            if(requestedSeats.contains(showSeatEntity.getSeatNo()))
            {
                totalAmount = totalAmount + showSeatEntity.getPrice();
                showSeatEntity.setBooked(true);
                showSeatEntity.setBookedAt(new Date());
            }
        }

        ticketEntity.setTotalAmount(totalAmount);

        //Setting the other attributes for the ticketEntity
        ticketEntity.setMovieName(showEntity.getMovieEntity().getMovieName());
        ticketEntity.setShowDate(showEntity.getShowDate());
        ticketEntity.setShowTime(showEntity.getShowTime());
        ticketEntity.setTheatreName(showEntity.getTheaterEntity().getName());

        //We need to set that string that talked about requested Seats
        String allotedSeats = getAllotedSeatsfromShowSeats(requestedSeats);
        ticketEntity.setBookedSeats(allotedSeats);

        //Setting the foreign key attributes
        UserEntity userEntity = userRepository.findById(ticketEntryDto.getUserId()).get();
        ticketEntity.setUserEntity(userEntity);
        ticketEntity.setShowEntity(showEntity);

        //Save the parent
        ticketEntity = ticketRepository.save(ticketEntity);

        List<TicketEntity> ticketEntityList = showEntity.getListOfBookedTickets();
        ticketEntityList.add(ticketEntity);
        showEntity.setListOfBookedTickets(ticketEntityList);
        showRepository.save(showEntity);

        List<TicketEntity> ticketEntityList1 = userEntity.getBookedTickets();
        ticketEntityList1.add(ticketEntity);
        userEntity.setBookedTickets(ticketEntityList1);
        userRepository.save(userEntity);

        return "Ticket has successfully been added";
    }


    private String getAllotedSeatsfromShowSeats(List<String> requestedSeats)
    {
        String result = "";
        for(String seat : requestedSeats)
        {
            result = result + seat + ", ";
        }
        return result;
    }

    private boolean checkValidityOfRequestedSeats(TicketEntryDto ticketEntryDto)
    {
        int showId = ticketEntryDto.getShowId();
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();  // Have seat numbers

        ShowEntity showEntity = showRepository.findById(showId).get();
        List<ShowSeatEntity> listOfSeats = showEntity.getListOfShowSeats();

        //Iterating over the list Of Seats for that particular show
        for(ShowSeatEntity showSeatEntity : listOfSeats)
        {
            String seatNo = showSeatEntity.getSeatNo();
            if(requestedSeats.contains(seatNo))
            {
                if(showSeatEntity.isBooked()==true)
                {
                    return false;   //Since this seat cant be occupied : returning false
                }
            }
        }
        //All the seats requested were available
        return true;
    }
}
