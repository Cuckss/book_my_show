package com.example.book_my_show.Convertors;

import com.example.book_my_show.EntryDtos.TheatreEntryDto;
import com.example.book_my_show.Models.TheatreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor

public class TheatreConvertor {

    public static TheatreEntity convertDtoToEntity(TheatreEntryDto theatreEntryDto){
        TheatreEntity theatreEntity= TheatreEntity.builder().name(theatreEntryDto.getName())
                .location(theatreEntryDto.getLocation()).build();
        return theatreEntity;
    }
}
