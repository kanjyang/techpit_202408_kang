package com.example.seat_reserve.domain.movie.model;

import lombok.Data;

@Data
public class OnAir {
    private int onCode;

    private int moCode;

    private int scCode;

    /** 映画名 **/
    private String moName;

    /** 上映館名 **/
    private String scName; 
}
