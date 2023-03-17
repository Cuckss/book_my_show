package com.example.book_my_show.Models;

import com.example.book_my_show.Enum.Genre;
import com.example.book_my_show.Enum.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="movie")
@Data
@Builder
@AllArgsConstructor

public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true,nullable = false)
    private String movieName;
    private Double rating;
    private int duration;
    @Enumerated(value=EnumType.STRING)
    private Language language;
    @Enumerated(value=EnumType.STRING)
    private Genre genre;

    public MovieEntity() {
    }


    @OneToMany(mappedBy = "movieEntity",cascade = CascadeType.ALL)
    List<ShowEntity> showEntityList=new ArrayList<>();

}
