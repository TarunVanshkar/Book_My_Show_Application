package com.example.Book_My_Show_Application.Converters;

import com.example.Book_My_Show_Application.Entities.MovieEntity;
import com.example.Book_My_Show_Application.EntryDtos.MovieEntryDto;

public class MovieConverter
{
    public static MovieEntity convertMovieEntryDtoToEntity(MovieEntryDto movieEntryDto)
    {
        MovieEntity movieEntity = MovieEntity.builder().movieName(movieEntryDto.getMovieName()).rating(movieEntryDto.getRating())
                .duration(movieEntryDto.getDuration()).language(movieEntryDto.getLanguage()).genre(movieEntryDto.getGenre())
                .build();

        return movieEntity;
    }
}
