package com.example.Book_My_Show_Application.Service;


import com.example.Book_My_Show_Application.Converters.TheaterConverter;
import com.example.Book_My_Show_Application.Entities.TheaterEntity;
import com.example.Book_My_Show_Application.Entities.TheaterSeatEntity;
import com.example.Book_My_Show_Application.EntryDtos.TheaterEntryDto;
import com.example.Book_My_Show_Application.Enums.SeatType;
import com.example.Book_My_Show_Application.Repository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService
{
    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    public String addTheater(TheaterEntryDto theaterEntryDto)
    {
        TheaterEntity theaterEntity = TheaterConverter.convertTheaterEntryDtoToEntity(theaterEntryDto);
        List<TheaterSeatEntity> theaterSeatEntityList = createTheaterSeats(theaterEntryDto, theaterEntity);
        return "Theater created successfully";
    }



    private List<TheaterSeatEntity> createTheaterSeats(TheaterEntryDto theaterEntryDto, TheaterEntity theaterEntity)
    {
        int noOfClassicSeats = theaterEntryDto.getClassicSeatCount();
        int noOfPremiumSeats = theaterEntryDto.getPremiumSeatCount();

        List<TheaterSeatEntity> theaterSeatEntityList = new ArrayList<>();

        //Created the classic Seats
        for(int count=1; count<=noOfClassicSeats; count++)
        {
            //We need to make a new TheaterSeatEntity
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder().seatType(SeatType.CLASSIC)
                    .seatNo(count+"C").theaterEntity(theaterEntity).build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }

        //Create the premium Seats
        for(int count=1; count<=noOfPremiumSeats; count++)
        {
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder().seatType(SeatType.PREMIUM)
                    .seatNo(count+"P").theaterEntity(theaterEntity).build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }

        theaterSeatRepository.saveAll(theaterSeatEntityList);
        return theaterSeatEntityList;
    }
}
