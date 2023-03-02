package com.example.Book_My_Show_Application.Controller;


import com.example.Book_My_Show_Application.EntryDtos.TheaterEntryDto;
import com.example.Book_My_Show_Application.EntryDtos.TicketEntryDto;
import com.example.Book_My_Show_Application.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController
{

    @Autowired
    TicketService ticketService;

    @PostMapping("/bookTicket")
    public String bookTicket(@RequestBody TicketEntryDto ticketEntryDto)
    {
        try
        {
            String response = ticketService.addTicket(ticketEntryDto);
            return response;
        }
        catch(Exception e)
        {
            return e.getMessage();
        }
    }
}
