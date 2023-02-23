package com.example.Book_My_Show_Application.EntryDtos;


import jakarta.persistence.Column;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserEntryDto
{
    private String name;

    private String age;

    private String email;

    private String mobileNo;

    private String address;
}
