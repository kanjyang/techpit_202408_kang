package com.example.seat_reserve.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ValidateService{
    


    /**
     * パラメータの有効性チェック
     * @param scCode
     * @return
     */
    public boolean validateScCode(String onCode){


        return false; //TODO
    }

}
