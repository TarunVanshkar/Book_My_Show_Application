package com.example.Book_My_Show_Application.Repository;

import com.example.Book_My_Show_Application.Entities.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {
}
