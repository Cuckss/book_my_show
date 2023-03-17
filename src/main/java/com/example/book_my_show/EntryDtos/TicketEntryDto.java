package com.example.book_my_show.EntryDtos;

import lombok.Data;

import java.util.List;

@Data
public class TicketEntryDto {
private  int showId;
//we can take list from the posteman
private List<String> requestedSeats;
private int userId;
}
