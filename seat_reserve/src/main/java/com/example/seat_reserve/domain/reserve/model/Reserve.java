package com.example.seat_reserve.domain.reserve.model;

import lombok.Data;

@Data
public class Reserve {

    private int reNo;
    private int onCode;
    private String seatNum;
    private String deleteFlg;

}
