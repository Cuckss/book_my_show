package com.example.book_my_show.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="tickets")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String movieName;
    private LocalTime showTime;
    private LocalDate showDate;
    private String theatreName;
    private int totalAmount;
    private String ticketId= UUID.randomUUID().toString();
    private String bookedSeats;

//this is child wrt user;
    @ManyToOne
    @JoinColumn
    private UserEntity userEntity;

    //ticket is also child wrt show;
    @ManyToOne
    @JoinColumn
    private ShowEntity showEntity;
}
