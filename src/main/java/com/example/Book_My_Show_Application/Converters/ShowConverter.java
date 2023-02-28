package com.example.Book_My_Show_Application.Converters;

import com.example.Book_My_Show_Application.Entities.ShowEntity;
import com.example.Book_My_Show_Application.EntryDtos.ShowEntryDto;

public class ShowConverter
{
    public static ShowEntity convertShowEntryDtoToEntity(ShowEntryDto showEntryDto)
    {
        ShowEntity showEntity = ShowEntity.builder().showDate(showEntryDto.getLocalDate())
                                    .showTime(showEntryDto.getLocalTime())
                                    .showType(showEntryDto.getShowType()).build();

        return showEntity;
    }
}
