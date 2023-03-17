package com.example.book_my_show.Service;

import com.example.book_my_show.Convertors.UserConvertor;
import com.example.book_my_show.EntryDtos.UserEntryDto;
import com.example.book_my_show.Models.*;
import com.example.book_my_show.Repository.TheatreRepository;
import com.example.book_my_show.Repository.TicketRepository;
import com.example.book_my_show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
   @Autowired
   UserRepository userRepository;
   @Autowired
    TheatreRepository theatreRepository;
   @Autowired
    TicketRepository ticketRepository;
   public String addUser(UserEntryDto userEntryDto) throws Exception{
      //creating the objects using builder
//
//      UserEntity userEntity=UserEntity.builder().age(userEntryDto.getAge()).name(userEntryDto.getName()).address(userEntryDto.getAddress())
//              .email(userEntryDto.getEmail()).mobNo(userEntryDto.getMobNo()).build();
//     userRepository.save(userEntity);

//        UserEntity userEntity=new UserEntity();
//        userEntity.setName(userEntryDto.getName());
//       return "User Added Successfully";

      UserEntity userEntity= UserConvertor.convertDtoToEntity(userEntryDto);
      userRepository.save(userEntity);
      return "User Added Successfully";
   }
//    public String allTicketsBooked(int userId){
//      UserEntity userEntity=userRepository.findById(userId).get();
//      TicketEntity ticketEntity=ticketRepository.
//
//      userEntity.setListOfBookedTickets();
//    }


}
