package com.example.Book_My_Show_Application.Converters;

import com.example.Book_My_Show_Application.Entities.UserEntity;
import com.example.Book_My_Show_Application.EntryDtos.UserEntryDto;

public class UserConverter
{
    //Static is kept to avoid calling it via objects/instances
    public static UserEntity convertUserDtoToEntity(UserEntryDto userEntryDto)
    {
        UserEntity userEntity = UserEntity.builder().name(userEntryDto.getName()).age(userEntryDto.getAge())
                .email(userEntryDto.getEmail()).mobileNo(userEntryDto.getMobileNo()).address(userEntryDto.getAddress()).build();

        return userEntity;
    }
}
