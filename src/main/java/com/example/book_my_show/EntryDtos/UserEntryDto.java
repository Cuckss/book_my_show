package com.example.book_my_show.EntryDtos;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NonNull;
@Data
public class UserEntryDto {

    private String name;
    private String email;
    private int age;
    private String mobNo;
    private String address;
}
