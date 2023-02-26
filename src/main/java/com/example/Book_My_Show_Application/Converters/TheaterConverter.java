package com.example.Book_My_Show_Application.Converters;

import com.example.Book_My_Show_Application.Entities.TheaterEntity;
import com.example.Book_My_Show_Application.EntryDtos.TheaterEntryDto;

public class TheaterConverter
{
    public static TheaterEntity convertTheaterEntryDtoToEntity(TheaterEntryDto theaterEntryDto)
    {
        return TheaterEntity.builder().name(theaterEntryDto.getName()).location(theaterEntryDto.getLocation())
                .build();
    }
}
