package com.example.seat_reserve.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seat_reserve.app.service.MovieService;
import com.example.seat_reserve.domain.movie.form.SeatForm;
import com.example.seat_reserve.domain.movie.model.Seat;

@RestController
@RequestMapping("ajax")
@CrossOrigin
public class AjaxController {
    
    @Autowired
    MovieService movieService;

    @PostMapping("/checkSeat")

    public ResponseEntity<Map<String, Object>> checkSeat(@RequestBody Map<String, String> reqMap){
    

        Map<String, Object> response = new HashMap<>();

        List<String> seats = new ArrayList<>();
        seats.add(reqMap.get("seat"));
        
        if(!movieService.checkCanReserveSeat(Integer.parseInt(reqMap.get("onCode")) , seats)){
            response.put("success",false);
        }else{
            response.put("success",true);
        }
        
        return ResponseEntity.ok(response);
    }


}
