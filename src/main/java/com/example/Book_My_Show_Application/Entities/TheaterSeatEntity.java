package com.example.Book_My_Show_Application.Entities;


import com.example.Book_My_Show_Application.Enums.SeatType;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theater_seats")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheaterSeatEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private String seatNo;

    // Connect Theatre with TheatreSeats
    @ManyToOne
    @JoinColumn
    private TheaterEntity theaterEntity;   // Since it is child so we always need to set this because it is foreign key attribute
                                           // so it will identify who is my parent
}
