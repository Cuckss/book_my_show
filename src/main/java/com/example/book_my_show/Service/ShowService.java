package com.example.book_my_show.Service;

import com.example.book_my_show.Convertors.ShowConvertor;
import com.example.book_my_show.EntryDtos.ShowEntryDto;
import com.example.book_my_show.Enum.SeatType;
import com.example.book_my_show.Models.*;
import com.example.book_my_show.Repository.MovieRepository;
import com.example.book_my_show.Repository.ShowRepository;
import com.example.book_my_show.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowEntryDto showEntryDto) throws Exception {
        //created the entity
        ShowEntity showEntity = ShowConvertor.convertShowDtoToEntity(showEntryDto);
        //now firstly getting the attributes of parent
        MovieEntity movieEntity = movieRepository.findById(showEntryDto.getMovieId()).get();
        TheatreEntity theatreEntity = theatreRepository.findById(showEntryDto.getTheatreId()).get();

        //now we have to set these attribute of foreign key
        showEntity.setMovieEntity(movieEntity);
        showEntity.setTheatreEntity(theatreEntity);
        //now we also have to set showseatentity attributes
        List<ShowSeatEntity> showSeatEntityList = createShowSeatList(showEntryDto, showEntity);
        showEntity.setShowSeatEntityList(showSeatEntityList);
        //now my goal is to create showseat Entity;

        //now parent's also needs to be updated
//        List<ShowEntity> showEntityList = movieEntity.getShowEntityList();
//        showEntity.setShowSeatEntityList(showSeatEntityList);
        //show entity can be saved in two ways 1st.directly save or 2nd.save and get that that entity

        showEntity = showRepository.save(showEntity);
//        showEntityList.add(showEntity);
//        movieEntity.setShowEntityList(showEntityList);
//        movieRepository.save(movieEntity);
        movieEntity.getShowEntityList().add(showEntity);
        theatreEntity.getShowEntityList().add(showEntity);


//        List<ShowEntity>showEntityList1=theatreEntity.getShowEntityList();
//        showEntityList1.add(showEntity);
//        theatreEntity.setShowEntityList(showEntityList1);
        movieRepository.save(movieEntity);
        theatreRepository.save(theatreEntity);

        return "Show Added Successfully";
    }

    public List<ShowSeatEntity> createShowSeatList(ShowEntryDto showEntryDto, ShowEntity showEntity) throws Exception {
        //now the goal is to create showseat entity
        //we need to set its attributes
        /*incase to find the showSeats that how many seats are there for that show firstly we have to find
        that show's particular theatre.
        After finding that thetare we have to find its seats
         */
        TheatreEntity theatreEntity = showEntity.getTheatreEntity();
        //we got the theatreEntity
        List<TheatreSeatsEntity> theatreSeatsEntityList = theatreEntity.getTheatreSeatsEntityList();
        List<ShowSeatEntity> showSeatEntityList = new ArrayList<>();
        for (TheatreSeatsEntity theatreSeatsEntity : theatreSeatsEntityList) {
            ShowSeatEntity showSeatEntity = new ShowSeatEntity();
            showSeatEntity.setSeatNo(theatreSeatsEntity.getSeatNo());
            showSeatEntity.setSeatType(theatreSeatsEntity.getSeatType());
            if (showSeatEntity.getSeatType().equals(SeatType.CLASSIC)) {
                showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());
            } else {
                showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());
            }
            showSeatEntity.setBooked(false);
            showSeatEntity.setShowEntity(showEntity);//we have to set foreign key for the showseat
            showSeatEntityList.add(showSeatEntity);
        }
        return showSeatEntityList;
    }

    public List<LocalTime> getShowTimings(int theatreId, int movieId) {
        TheatreEntity theatreEntity = theatreRepository.findById(theatreId).get();
        MovieEntity movieEntity = movieRepository.findById(movieId).get();
        List<LocalTime> movieTimeList = new ArrayList<>();
        List<ShowEntity> showEntityList = theatreEntity.getShowEntityList();
        for (ShowEntity showEntity : showEntityList) {
            if (showEntity.getMovieEntity().equals(movieEntity)) {
                movieTimeList.add(showEntity.getShowTime());
            }
        }
        return movieTimeList;
    }

}