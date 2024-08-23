package com.example.seat_reserve.app.service;

import java.util.List;

import com.example.seat_reserve.domain.reserve.model.Reserve;

public interface ReserveRepository {
    
    List<Reserve> findReservedSeats(int onCode, List<String> seats);

    void insertReservation(Reserve reserve);
}
