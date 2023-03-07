package com.example.Book_My_Show_Application.Entities;


import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String age;

    @Column(unique = true, nullable = false)
    private String email;

    @NonNull
    @Column(unique = true)
    private String mobileNo;

    private String address;

    // Connect UserEntity with TicketEntity
    // It is parent wrt to TicketEntity
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    List<TicketEntity> bookedTickets = new ArrayList<>();
}
