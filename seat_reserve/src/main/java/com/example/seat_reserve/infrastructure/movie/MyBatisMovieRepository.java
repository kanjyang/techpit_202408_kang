package com.example.seat_reserve.infrastructure.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.seat_reserve.app.service.MovieRepository;
import com.example.seat_reserve.domain.movie.model.Movie;
import com.example.seat_reserve.domain.movie.model.OnAir;
import com.example.seat_reserve.domain.movie.model.Seat;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisMovieRepository implements MovieRepository{
    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<Movie> findAll(){
        return movieMapper.findAll();
    }


    @Override
    public List<OnAir> findAllOnAir(){
        return movieMapper.findAllOnAir();
    }

    @Override
    public List<Seat> findSelectedScreenSeat(int scCode){
        return movieMapper.findSelectedScreenSeat(scCode);
    }
}
