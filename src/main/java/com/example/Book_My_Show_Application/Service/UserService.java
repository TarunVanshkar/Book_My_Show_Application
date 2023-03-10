package com.example.Book_My_Show_Application.Service;

import com.example.Book_My_Show_Application.Converters.UserConverter;
import com.example.Book_My_Show_Application.Entities.UserEntity;
import com.example.Book_My_Show_Application.EntryDtos.UserEntryDto;
import com.example.Book_My_Show_Application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{

    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDto userEntryDto) throws Exception, NullPointerException
    {
        UserEntity userEntity = UserConverter.convertUserDtoToEntity(userEntryDto);   // Calling static method through class name
        userRepository.save(userEntity);
        return "User added successfully";
    }
}

//    public void addUser(UserEntryDto userEntryDto)
//    {
//        // Here we need to convert and save
//        /*
//            Old method : create an object and set attributes.
//         */
////
////        userEntity.setAge(userEntryDto.getAge())
////                userEntity.setAddress()
//
//        //creating the object of UserEntity with the help of builder annotation
//        UserEntity userEntity = UserEntity.builder().name(userEntryDto.getName()).age(userEntryDto.getAge())
//                .email(userEntryDto.getEmail()).mobileNo(userEntryDto.getMobileNo())
//                .address(userEntryDto.getAddress()).build();
//
//        //This is to set all of the attributes in 1 go.
//
//
//        userRepository.save(userEntity);
//    }
// @Builder annotation should be written on the top of that class whose object needs to be created
// @AllArgsConstructor should be there along with @Builder