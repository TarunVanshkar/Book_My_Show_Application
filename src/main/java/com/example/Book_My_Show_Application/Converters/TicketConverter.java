package com.example.Book_My_Show_Application.Converters;

import com.example.Book_My_Show_Application.Entities.TicketEntity;
import com.example.Book_My_Show_Application.EntryDtos.TicketEntryDto;

public class TicketConverter
{

    public static TicketEntity convertTicketEntryDtoToEntity(TicketEntryDto ticketEntryDto)
    {
        TicketEntity ticketEntity = new TicketEntity();
        return ticketEntity;
    }
}
