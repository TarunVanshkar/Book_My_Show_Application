package com.example.Book_My_Show_Application.Entities;

import com.example.Book_My_Show_Application.Enums.SeatType;
//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "show_seats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeatEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isBooked;

    private int price;    //price of CLASSIC or PREMIUM Seat for that particular show

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private Date bookedAt;

    // Connect ShowEntity with ShowSeatEntity
    // It is child wrt to ShowEntity
    @ManyToOne
    @JoinColumn
    private ShowEntity showEntity;
}
