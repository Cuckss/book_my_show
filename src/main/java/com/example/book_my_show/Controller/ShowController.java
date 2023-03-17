package com.example.book_my_show.Controller;

import com.example.book_my_show.EntryDtos.ShowEntryDto;
import com.example.book_my_show.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    ShowService showService;
    @PostMapping("/add")
    public ResponseEntity<String>addShow(@RequestBody ShowEntryDto showEntryDto){
     try{
         String response= showService.addShow(showEntryDto);
         return new ResponseEntity<>(response, HttpStatus.CREATED);
     } catch(Exception e){
         String result="cannot be able to add show";
         return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
     }
    }
    @GetMapping("/timing-list/{theatreId}/{movieId}")
    public ResponseEntity<List<LocalTime>>getShowTimings(@PathVariable("theatreId") Integer theatreId,@PathVariable("movieId") Integer movieId){
        List<LocalTime>getlist=showService.getShowTimings(theatreId,movieId);
        return new ResponseEntity<>(getlist,HttpStatus.BAD_REQUEST);
    }
}
