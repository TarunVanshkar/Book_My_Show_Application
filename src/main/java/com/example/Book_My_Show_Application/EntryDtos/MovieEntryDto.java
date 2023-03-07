package com.example.Book_My_Show_Application.EntryDtos;

import com.example.Book_My_Show_Application.Enums.Genre;
import com.example.Book_My_Show_Application.Enums.Language;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
import javax.persistence.*;
import lombok.Data;

@Data
public class MovieEntryDto
{
    private String movieName;

    private double rating;

    private int duration;

    private Language language;

    private Genre genre;
}
