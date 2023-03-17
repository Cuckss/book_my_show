package com.example.book_my_show.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="theatres")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheatreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;
    //here theatre is parent to theatreSeats;
    @OneToMany(mappedBy ="theatreEntity",cascade = CascadeType.ALL)
    private List<TheatreSeatsEntity> theatreSeatsEntityList=new ArrayList<>();

    //here also theatre is parent wrt show;
    @OneToMany(mappedBy = "theatreEntity",cascade = CascadeType.ALL)
    private List<ShowEntity>showEntityList=new ArrayList<>();

}
