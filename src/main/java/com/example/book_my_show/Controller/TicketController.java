package com.example.book_my_show.Controller;

import com.example.book_my_show.EntryDtos.TicketEntryDto;
import com.example.book_my_show.Repository.TicketRepository;
import com.example.book_my_show.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @PostMapping("/book")
    public String bookTicket(@RequestBody  TicketEntryDto ticketEntryDto){
    try{
        String response= ticketService.addTicket(ticketEntryDto);
        return String.valueOf(new ResponseEntity<>(response, HttpStatus.CREATED));
    }catch(Exception e){
        return String.valueOf(new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST));
    }
    }
}
