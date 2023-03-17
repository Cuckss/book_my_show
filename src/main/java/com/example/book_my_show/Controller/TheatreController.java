package com.example.book_my_show.Controller;

import com.example.book_my_show.EntryDtos.TheatreEntryDto;
import com.example.book_my_show.Models.MovieEntity;
import com.example.book_my_show.Models.TheatreEntity;
import com.example.book_my_show.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theaters")
public class TheatreController {
    @Autowired
    TheatreService theatreService;
@PostMapping("/add")
    public ResponseEntity<String> addTheater(@RequestBody TheatreEntryDto theatreEntryDto) {
        try {
            String response = theatreService.addTheatre(theatreEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/theater-show-particular-movie/{theaterId}")
    public ResponseEntity<List<TheatreEntity>>theaterShowParticularMovie(@PathVariable("theaterId") Integer theaterId){
       List<TheatreEntity>response=theatreService.theaterShowParticularMovie(theaterId);
       return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/unique-locations")
    public ResponseEntity<List<String>>uniqueLocations(){
    List<String>response=theatreService.uniqueLocations();
    return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
