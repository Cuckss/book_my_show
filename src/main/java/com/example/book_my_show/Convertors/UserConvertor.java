package com.example.book_my_show.Convertors;

import com.example.book_my_show.EntryDtos.UserEntryDto;
import com.example.book_my_show.Models.UserEntity;

public class UserConvertor {
    //static is kept to avoid calling it by object
  public static UserEntity convertDtoToEntity(UserEntryDto userEntryDto){
        //we should return
        UserEntity userEntity= UserEntity.builder().age(userEntryDto.getAge()).name(userEntryDto.getName()).address(userEntryDto.getAddress())
                .email(userEntryDto.getEmail()).mobNo(userEntryDto.getMobNo()).build();
        return userEntity;
    }
}
