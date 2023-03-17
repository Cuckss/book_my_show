package com.example.book_my_show.Controller;

import com.example.book_my_show.EntryDtos.UserEntryDto;
import com.example.book_my_show.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserEntryDto userEntryDto) {
        try {
            String response = userService.addUser(userEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            String result = "Could Not Create User";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }



}