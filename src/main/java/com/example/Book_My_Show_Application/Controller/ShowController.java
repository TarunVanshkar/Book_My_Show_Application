package com.example.Book_My_Show_Application.Controller;

import com.example.Book_My_Show_Application.EntryDtos.ShowEntryDto;
import com.example.Book_My_Show_Application.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController
{
    @Autowired
    ShowService showService;

    @PostMapping("/addShow")
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto)
    {
        try
        {
            String response = showService.addShow(showEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
