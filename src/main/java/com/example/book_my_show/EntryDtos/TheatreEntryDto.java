package com.example.book_my_show.EntryDtos;

import lombok.Data;

@Data
public class TheatreEntryDto {
    private String name;
    private String location;
     private int classicSeatSCount;
     private int premiumSeatsCount;
}
