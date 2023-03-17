package com.example.book_my_show.Models;

import com.example.book_my_show.Enum.ShowType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="shows")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate showDate;
    private LocalTime showTime;
    @Enumerated(value=EnumType.STRING)
    private ShowType showType;
    @CreationTimestamp
    private Date createdOn;
    @CreationTimestamp
    private Date updatedOn;
    //show is child wrt movie so,
    @ManyToOne
    @JoinColumn
    private MovieEntity movieEntity;

    //show is also child wrt theatre;
    @ManyToOne
    @JoinColumn
    private TheatreEntity theatreEntity;

    //show is also parent wrt ticket;
    @OneToMany(mappedBy = "showEntity",cascade = CascadeType.ALL)
    private List<TicketEntity> listOfBookedTickets=new ArrayList<>();

    //show is parent wrt to showseats;
    @OneToMany(mappedBy = "showEntity",cascade = CascadeType.ALL)
    private List<ShowSeatEntity> showSeatEntityList=new ArrayList<>();

}
