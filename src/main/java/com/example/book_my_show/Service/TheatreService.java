package com.example.book_my_show.Service;

import com.example.book_my_show.Convertors.TheatreConvertor;
import com.example.book_my_show.EntryDtos.TheatreEntryDto;
import com.example.book_my_show.Enum.SeatType;
import com.example.book_my_show.Models.MovieEntity;
import com.example.book_my_show.Models.ShowEntity;
import com.example.book_my_show.Models.TheatreEntity;
import com.example.book_my_show.Models.TheatreSeatsEntity;
import com.example.book_my_show.Repository.MovieRepository;
import com.example.book_my_show.Repository.TheatreRepository;
import com.example.book_my_show.Repository.TheatreSeatRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@Builder
@Service
public class TheatreService {

    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    TheatreSeatRepository theatreSeatRepository;
    @Autowired
    MovieRepository movieRepository;
   public String addTheatre(TheatreEntryDto theatreEntryDto) throws Exception{
       if(theatreEntryDto.getName()==null || theatreEntryDto.getLocation()==null){
           throw new Exception("name and location needs to be valid");
       }
       TheatreEntity theatreEntity=TheatreConvertor.convertDtoToEntity(theatreEntryDto);
       List<TheatreSeatsEntity> theatreSeatsEntities=createTheatreSeats(theatreEntryDto,theatreEntity);
       theatreEntity.setTheatreSeatsEntityList(theatreSeatsEntities);
       theatreRepository.save(theatreEntity);
      return "Theatre added successfully";
   }
   private List<TheatreSeatsEntity> createTheatreSeats(TheatreEntryDto theatreEntryDto,TheatreEntity theatreEntity){
       int noClassicSeats=theatreEntryDto.getClassicSeatSCount();
       int noPremiumSeats=theatreEntryDto.getPremiumSeatsCount();
       List<TheatreSeatsEntity>theatreSeatsEntityList=new ArrayList<>();
       //created the classic seats;
       for(int count=1;count<=noClassicSeats;count++){
           //we needed to make new theatreSeatEntity;
         TheatreSeatsEntity theatreSeatsEntity=TheatreSeatsEntity.builder().seatType(SeatType.CLASSIC)
                 .seatNo(count+"C").theatreEntity(theatreEntity).build();
       theatreSeatsEntityList.add(theatreSeatsEntity);
       }
       //create premium seats;
       for(int count=1;count<=noPremiumSeats;count++){
           TheatreSeatsEntity theatreSeatsEntity=TheatreSeatsEntity.builder().seatType(SeatType.PREMIUM).
                   seatNo(count+"P").theatreEntity(theatreEntity).build();
           theatreSeatsEntityList.add(theatreSeatsEntity);
       }
//       theatreSeatRepository.saveAll(theatreSeatsEntityList);
       return theatreSeatsEntityList;
   }
   public List<TheatreEntity> theaterShowParticularMovie(Integer movieId){
       MovieEntity movieEntity=movieRepository.findById(movieId).get();
       List<TheatreEntity>theatreEntityList=new ArrayList<>();
       List<ShowEntity>listOfShows=movieEntity.getShowEntityList();
       for(ShowEntity showEntity:listOfShows){
         theatreEntityList.add(showEntity.getTheatreEntity());
       }
       return theatreEntityList;
   }
   public List<String> uniqueLocations(){
       List<TheatreEntity>listOfTheaters=theatreRepository.findAll();
       HashSet<String>set=new HashSet<>();
       for(TheatreEntity theatreEntity:listOfTheaters){
           set.add(theatreEntity.getLocation());
       }
       return new ArrayList<>(set);
   }
   public int uniqueLocation(){
       int count=0;
       List<TheatreEntity>listOfTheaters=theatreRepository.findAll();
       HashSet<String>set=new HashSet<>();
       for(TheatreEntity theatreEntity:listOfTheaters){
           set.add(theatreEntity.getLocation());
           count++;
       }
       return count;
   }
   public List<TheatreEntity> showingMovieOnParticularTime(LocalTime time){
       List<TheatreEntity>theatreEntityList=theatreRepository.findAll();
       List<TheatreEntity>listOfTheatresShowingMovieOnTime=new ArrayList<>();
       for(TheatreEntity theatreEntity:theatreEntityList){
           List<ShowEntity>showEntityList=theatreEntity.getShowEntityList();
           for(ShowEntity showEntity:showEntityList){
               if(showEntity.getShowTime().equals(time)){
                   listOfTheatresShowingMovieOnTime.add(theatreEntity);
               }
           }
       }
       return listOfTheatresShowingMovieOnTime;
   }

}
