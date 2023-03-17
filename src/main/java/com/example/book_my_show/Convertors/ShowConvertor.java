package com.example.book_my_show.Convertors;

import com.example.book_my_show.EntryDtos.ShowEntryDto;
import com.example.book_my_show.Models.ShowEntity;
import lombok.Builder;


public class ShowConvertor {
    public static ShowEntity convertShowDtoToEntity(ShowEntryDto showEntryDto){
        ShowEntity showEntity= ShowEntity.builder()
                .showDate(showEntryDto.getShowDate())
                .showTime(showEntryDto.getShowTime())
                .showType(showEntryDto.getShowType())
                               .build();
        return showEntity;
    }
}
