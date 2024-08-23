package com.example.seat_reserve.domain.movie.form;

import java.util.List;

import lombok.Data;

@Data
public class SeatForm {
    
    private int onCode;

    private int scCode;

    private List<String> seats;
}
