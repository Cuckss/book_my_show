package com.example.book_my_show.Convertors;

import com.example.book_my_show.EntryDtos.TicketEntryDto;
import com.example.book_my_show.Models.TicketEntity;

public class TicketConvertor {
    public static TicketEntity convertEntryDtoToTicketEntity(TicketEntryDto ticketEntryDto){
      TicketEntity ticketEntity=new TicketEntity();
      return ticketEntity;
    }
}
