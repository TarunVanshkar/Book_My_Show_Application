package com.example.Book_My_Show_Application.Entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theaters")
@Data
@NoArgsConstructor
public class TheatreEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String location;

    // Connect Theatre with TheatreSeats
    //This is the parent wrt to theaterSeats
    @OneToMany(mappedBy = "theatreEntity", cascade = CascadeType.ALL)
    private List<TheatreSeatEntity> theatreSeatEntityList = new ArrayList<>();

    // Connect ShowEntity with TheatreEntity
    @OneToMany(mappedBy = "theatreEntity", cascade = CascadeType.ALL)
    private List<ShowEntity> showEntityList;
}
