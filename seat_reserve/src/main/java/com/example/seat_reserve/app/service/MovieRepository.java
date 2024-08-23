package com.example.seat_reserve.app.service;

import java.util.List;

import com.example.seat_reserve.domain.movie.model.Movie;
import com.example.seat_reserve.domain.movie.model.OnAir;
import com.example.seat_reserve.domain.movie.model.Seat;

public interface MovieRepository {
    
    List<Movie> findAll();

    List<OnAir> findAllOnAir();

    List<Seat> findSelectedScreenSeat(int scCode);
}
