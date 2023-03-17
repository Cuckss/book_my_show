package com.example.book_my_show.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class UserEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;
    private String name;

    @Column(unique = true,nullable = false)
    private String email;
    private int age;
    @NonNull
    @Column(unique = true)
    private String mobNo;
    private String address;

    //ticket is child wrt tickets
    @OneToMany(mappedBy = "userEntity",cascade=CascadeType.ALL)
    private List<TicketEntity> listOfBookedTickets=new ArrayList<>();

}
