package com.example.seat_reserve.infrastructure.reserve;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.seat_reserve.domain.reserve.model.Reserve;

@Mapper
public interface ReserveMapper {
    

    List<Reserve> findReservedSeats(int onCode, List<String> seats);

    void insertReservation(Reserve reserve);
}
