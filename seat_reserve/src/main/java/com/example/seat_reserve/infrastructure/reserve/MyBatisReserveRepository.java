package com.example.seat_reserve.infrastructure.reserve;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.seat_reserve.app.service.ReserveRepository;
import com.example.seat_reserve.domain.reserve.model.Reserve;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisReserveRepository implements ReserveRepository{
    
    @Autowired
    ReserveMapper reserveMapper;
    
    @Override
    public List<Reserve> findReservedSeats(int onCode, List<String> seats) {
       
        return  reserveMapper.findReservedSeats(onCode, seats);
    }

    @Override
    public void insertReservation(Reserve reserve) {
        reserveMapper.insertReservation(reserve);        
    }
    

}
