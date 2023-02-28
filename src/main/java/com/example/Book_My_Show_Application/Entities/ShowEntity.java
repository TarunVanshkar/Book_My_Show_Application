package com.example.Book_My_Show_Application.Entities;

import com.example.Book_My_Show_Application.Enums.ShowType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shows")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate showDate;

    private LocalTime showTime;

    @Enumerated(value = EnumType.STRING)
    private ShowType showType;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;


    // Connect ShowEntity with MovieEntity
    //This is child wrt to the movieEntity
    @ManyToOne
    @JoinColumn
    MovieEntity movieEntity;

    // Connect ShowEntity with TheaterEntity
    //This is child wrt to the TheaterEntity
    @ManyToOne
    @JoinColumn
    TheaterEntity theaterEntity;

    // Connect TicketEntity with ShowEntity
    //Show is parent wrt to ticket
    @OneToMany(mappedBy = "showEntity", cascade = CascadeType.ALL)
    private List<TicketEntity> listOfBookedTickets = new ArrayList<>();

    // Connect ShowEntity with ShowSeatEntity
    // It is parent wrt to ShowSeatsEntity
    @OneToMany(mappedBy = "showEntity", cascade = CascadeType.ALL)
    private List<ShowSeatEntity> listOfShowSeats = new ArrayList<>();
}
