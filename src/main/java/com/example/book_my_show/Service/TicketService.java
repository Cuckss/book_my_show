package com.example.book_my_show.Service;

import com.example.book_my_show.Convertors.TicketConvertor;
import com.example.book_my_show.EntryDtos.TicketEntryDto;
import com.example.book_my_show.Enum.SeatType;
import com.example.book_my_show.Models.ShowEntity;
import com.example.book_my_show.Models.ShowSeatEntity;
import com.example.book_my_show.Models.TicketEntity;
import com.example.book_my_show.Models.UserEntity;
import com.example.book_my_show.Repository.ShowRepository;
import com.example.book_my_show.Repository.TicketRepository;
import com.example.book_my_show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;
    public String addTicket(TicketEntryDto ticketEntryDto) throws Exception {
       //convert entryDto to entity
        TicketEntity ticketEntity = TicketConvertor.convertEntryDtoToTicketEntity(ticketEntryDto);
      //  ShowEntity showEntity=showRepository.findById(ticketEntity.getId()).get();
        //Validation;---Now I need to check if the requested seats are avsailbale or not;
       boolean isValid=checkValidityOfRequestedSeats(ticketEntryDto);
       if(isValid==false){
           throw new Exception("Requested seats are not available");
       }
       //if we came here we assume that all seats are valid and available

        //we have to calculate totalPrice;
        ShowEntity showEntity=showRepository.findById(ticketEntryDto.getShowId()).get();
       List<ShowSeatEntity>showSeatEntityList=showEntity.getShowSeatEntityList();
       List<String>requestedSeats=ticketEntryDto.getRequestedSeats();
       int totalAmount=0;
       for(ShowSeatEntity showSeatEntity:showSeatEntityList){
           if(requestedSeats.contains(showSeatEntity.getSeatNo())){
               totalAmount+=showSeatEntity.getPrice();
               showSeatEntity.setBooked(true);
               showSeatEntity.setBookedAt(new Date());
           }
       }

       ticketEntity.setTotalAmount(totalAmount);
       //setting the other required attributes;
       ticketEntity.setMovieName(showEntity.getMovieEntity().getMovieName());
       ticketEntity.setShowDate(showEntity.getShowDate());
       ticketEntity.setShowTime(showEntity.getShowTime());
       ticketEntity.setTheatreName(showEntity.getTheatreEntity().getName());

       //Now setting the foreign key attributes;
        UserEntity userEntity=userRepository.findById(ticketEntryDto.getUserId()).get();
        ticketEntity.setUserEntity(userEntity);
        ticketEntity.setShowEntity(showEntity);
       /*firstly we should save the ticketentity so that after if we cascade it twice it will not
       be affected by cascading since the primary key already set then it doesnot create the duplicate entries again
        */
       ticketEntity=ticketRepository.save(ticketEntity);
        //saving the parents;
        List<TicketEntity>ticketEntityList=showEntity.getListOfBookedTickets();
        ticketEntityList.add(ticketEntity);
        showEntity.setListOfBookedTickets(ticketEntityList);
        showRepository.save(showEntity);

        List<TicketEntity>ticketEntityList1=userEntity.getListOfBookedTickets();
        ticketEntityList1.add(ticketEntity);
        userEntity.setListOfBookedTickets(ticketEntityList1);
        userRepository.save(userEntity);

        //we need to set the attribute that talked about requested seats
        String allotedSeats=getAllotedSeatsFromShowSeats(requestedSeats);
        ticketEntity.setBookedSeats(allotedSeats);

        return "Ticket has been added successfully";
    }
    private String getAllotedSeatsFromShowSeats(List<String>requestedSeats){
        String result="";
        for(String seats:requestedSeats){
            result+=seats+", ";
        }
return result;
    }
    private boolean checkValidityOfRequestedSeats(TicketEntryDto ticketEntryDto){
   int showId=ticketEntryDto.getShowId();
        List<String>requestedSeats=ticketEntryDto.getRequestedSeats();
        ShowEntity showEntity=showRepository.findById(showId).get();
        List<ShowSeatEntity>listOfSeats=showEntity.getShowSeatEntityList();
        // now we will iterate through these listOfShows
        for(ShowSeatEntity showSeatEntity:listOfSeats){
            String seatNo=showSeatEntity.getSeatNo();
            if(requestedSeats.contains(seatNo)){//here we got the correct seat
                if(showSeatEntity.isBooked()==true){//but the seat is already booked so it cant be occuupied
                    return false;    //so we are returning false;
                }
            }
        }
     return true;   //here all the requested seats are available
    }
    public String cancelTicket(TicketEntryDto ticketEntryDto){
        UserEntity userEntity=userRepository.findById(ticketEntryDto.getUserId()).get();
        ShowEntity showEntity=showRepository.findById(ticketEntryDto.getShowId()).get();
        List<ShowSeatEntity>showSeatEntityList=showEntity.getShowSeatEntityList();
        List<String>requestedSeats=ticketEntryDto.getRequestedSeats();
        int refund=0;
        for(ShowSeatEntity showSeatEntity:showSeatEntityList){
            String seatno=showSeatEntity.getSeatNo();
            if(requestedSeats.contains(seatno)){
                    refund+=showSeatEntity.getPrice();
                showSeatEntity.setBooked(false);
            }
        }
//
//       int size= showEntity.getShowSeatEntityList().size()+requestedSeats.size();
////        List<TicketEntity>ticketEntityList=show
        return "Ticket cancelled Sucessfully";
    }
}
