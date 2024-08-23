package com.example.seat_reserve.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.seat_reserve.domain.movie.model.Movie;
import com.example.seat_reserve.domain.reserve.model.Reserve;

@Service
public class ReserveService {

    @Autowired
    ReserveRepository reserveRepository;
    
    /**
     * DB格納用のリストの生成
     * @param onCode
     * @param seatNumList
     * @return
     */
    public List<Reserve> makeReserveList(int onCode, List<String> seatNumList){

        List<Reserve> result = new ArrayList<>();
        for(String seatNum : seatNumList){
            Reserve resv = new Reserve();
            resv.setOnCode(onCode);
            resv.setSeatNum(seatNum);
            resv.setDeleteFlg("0");
            result.add(resv);
        }
        return result;

    }

    
    /**
     * 
     * 予約情報のINSERT
     * @param reserveList
     */
    public void registerReserve(List<Reserve> reserveList){
        
        //リストをDBにINSERTする
        for(Reserve resv : reserveList){
            reserveRepository.insertReservation(resv);
        }
    }
}
