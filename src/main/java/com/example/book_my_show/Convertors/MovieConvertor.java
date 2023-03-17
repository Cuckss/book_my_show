package com.example.book_my_show.Convertors;

import com.example.book_my_show.EntryDtos.MovieEntryDto;
import com.example.book_my_show.Models.MovieEntity;

public class MovieConvertor {

    public static MovieEntity convertEntryDtoToEntity(MovieEntryDto movieEntryDto){
        MovieEntity movieEntity=MovieEntity.builder().movieName(movieEntryDto.getMovieName())
                .rating(movieEntryDto.getRating())
                .duration(movieEntryDto.getDuration())
                .genre(movieEntryDto.getGenre())
                .language(movieEntryDto.getLanguage())
                .build();
        return movieEntity;
    }
}

