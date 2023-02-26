package com.example.Book_My_Show_Application.Controller;


import com.example.Book_My_Show_Application.EntryDtos.TheaterEntryDto;
import com.example.Book_My_Show_Application.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController
{

    @Autowired
    TheaterService theaterService;

    public String addTheatre(@RequestBody TheaterEntryDto theaterEntryDto)
    {
        return theaterService.addTheater(theaterEntryDto);
    }
}
