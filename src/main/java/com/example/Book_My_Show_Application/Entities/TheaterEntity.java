package com.example.Book_My_Show_Application.Entities;


//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theaters")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheaterEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String location;

    // Connect Theatre with TheatreSeats
    //This is the parent wrt to theaterSeats
    @OneToMany(mappedBy = "theaterEntity", cascade = CascadeType.ALL)
    private List<TheaterSeatEntity> theaterSeatEntityList = new ArrayList<>();

    // Connect ShowEntity with TheaterEntity
    @OneToMany(mappedBy = "theaterEntity", cascade = CascadeType.ALL)
    private List<ShowEntity> showEntityList;
}
