package com.example.Book_My_Show_Application.Entities;


import com.example.Book_My_Show_Application.Enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theatre_seats")
@Data
@NoArgsConstructor
public class TheatreSeatEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private SeatType seatType;

    private String seatNo;

    // Connect Theatre with TheatreSeats
    @ManyToOne
    @JoinColumn
    private TheatreEntity theatreEntity;
}
