package com.example.book_my_show.Service;

import com.example.book_my_show.Convertors.MovieConvertor;
import com.example.book_my_show.EntryDtos.MovieEntryDto;
import com.example.book_my_show.Models.MovieEntity;
import com.example.book_my_show.Models.ShowEntity;
import com.example.book_my_show.Models.TheatreEntity;
import com.example.book_my_show.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
public String addMovie(MovieEntryDto movieEntryDto){
 MovieEntity movieEntity= MovieConvertor.convertEntryDtoToEntity(movieEntryDto);
 movieRepository.save(movieEntity);
 return"Movie Added Successfully";
}
    public MovieEntity maximumShows(){
        List<MovieEntity>movieEntityList=movieRepository.findAll();
        int max=0;
        MovieEntity movieEntity1 = null;
        for(MovieEntity movieEntity:movieEntityList){
         if(movieEntity.getShowEntityList().size()>max){
             max=movieEntity.getShowEntityList().size();
             movieEntity1=movieEntity;
         }
        }
        return movieEntity1;
    }

}
