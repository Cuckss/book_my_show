package com.example.book_my_show.Controller;

import com.example.book_my_show.EntryDtos.MovieEntryDto;
import com.example.book_my_show.Models.MovieEntity;
import com.example.book_my_show.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {
@Autowired
    MovieService movieService;
@PostMapping("/add")
    public ResponseEntity<String>addMovie(@RequestBody MovieEntryDto movieEntryDto){
    try{
      String response= movieService.addMovie(movieEntryDto);
      return new ResponseEntity<>(response, HttpStatus.CREATED);
    }catch(Exception e){
String result="cannot be able to create movie";
return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
    }
    }
    @GetMapping("/maxshowmovie")
    public ResponseEntity<MovieEntity>maximumShow(){
    MovieEntity response=movieService.maximumShows();
    return new ResponseEntity<>(response,HttpStatus.FOUND);
    }

}
