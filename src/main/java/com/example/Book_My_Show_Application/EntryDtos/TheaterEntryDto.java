package com.example.Book_My_Show_Application.EntryDtos;

import lombok.Data;

@Data
public class TheaterEntryDto
{
    //Attributes that we require
    private String name;
    private String location;

    // Custom
    private int classicSeatCount;
    private int premiumSeatCount;
}
