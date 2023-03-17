package com.example.book_my_show.Models;

import com.example.book_my_show.Enum.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="theatre_seats")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class TheatreSeatsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seatNo;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    @ManyToOne
    @JoinColumn    //(columnDefinition = "name")this annotation will set that as a foreign key in child table
    private TheatreEntity theatreEntity;

}
