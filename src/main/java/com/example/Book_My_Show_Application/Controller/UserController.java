package com.example.Book_My_Show_Application.Controller;


import com.example.Book_My_Show_Application.EntryDtos.UserEntryDto;
import com.example.Book_My_Show_Application.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController
{

    @Autowired
    UserService userService;


    @PostMapping("/addUser")
    public String addUser(@RequestBody UserEntryDto userEntryDto)
    {
        // Here we need to convert and save
        userService.addUser(userEntryDto);
        return "User added successfully";
    }
}
