package com.example.book_my_show.Repository;

import com.example.book_my_show.Models.TheatreSeatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreSeatRepository extends JpaRepository<TheatreSeatsEntity,Integer> {
}
