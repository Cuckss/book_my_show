package com.example.book_my_show.Models;

import com.example.book_my_show.Enum.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name="showseats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isBooked;
    private int price;
    private String seatNo;
    private Date bookedAt;
    @Enumerated(value=EnumType.STRING)
    private SeatType seatType;

    //showseats is child wrt to showentity
    @ManyToOne
    @JoinColumn
    private ShowEntity showEntity;
}
