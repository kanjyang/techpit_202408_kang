package com.example.seat_reserve.domain.movie.model;

import lombok.Data;

@Data
public class Seat {
    
    private int scCode;
    private String seatNum;
    private String seatDivRom;
    private String seatDivNum; 

    private boolean selected;
}
