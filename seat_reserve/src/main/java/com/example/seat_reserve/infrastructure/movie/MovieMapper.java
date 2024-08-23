package com.example.seat_reserve.infrastructure.movie;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.seat_reserve.domain.movie.model.Movie;
import com.example.seat_reserve.domain.movie.model.OnAir;
import com.example.seat_reserve.domain.movie.model.Seat;


@Mapper
public interface MovieMapper {

    List<Movie> findAll();

    List<OnAir> findAllOnAir();

    List<Seat> findSelectedScreenSeat(int scCode);
}
