package com.example.seat_reserve.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.seat_reserve.app.service.MovieService;
import com.example.seat_reserve.app.service.ReserveService;
import com.example.seat_reserve.app.service.ValidateService;
import com.example.seat_reserve.domain.movie.form.SeatForm;
import com.example.seat_reserve.domain.movie.model.OnAir;
import com.example.seat_reserve.domain.movie.model.Seat;
import com.example.seat_reserve.domain.reserve.model.Reserve;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("reserve")
@CrossOrigin
public class ReserveController {
    
    @Autowired
    private MovieService movieService;

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private ValidateService validateService;


    @GetMapping("/showAllOnAir")
    public String showAllOnAir(Model model){

        //全ての上映情報を表示する
        List<OnAir> onAirList = movieService.findAllOnAir();

        model.addAttribute("onAirList", onAirList);

        return "movie/onair_top_menu";

    }


    
    @GetMapping("/showSeat")
    public String showSelectedOnAirSeat(@RequestParam("onCode") String onCode, @RequestParam("scCode") String scCode, Model model, HttpSession session){
        
        //パラメータチェック
        if(!validateService.validateScCode(onCode)){
            
            //TODO Exception
        }

        //選択された上映情報の座席情報を取得する
        Map<String, List<Seat>> seatMap = movieService.makeSeatMap(Integer.parseInt(onCode),Integer.parseInt(scCode));

        //セッション情報保持
        SeatForm seatForm = new SeatForm();
        seatForm.setOnCode(Integer.parseInt(onCode));
        seatForm.setScCode(Integer.parseInt(scCode));
        session.setAttribute("sessionForm", seatForm);
        //モデル情報保持
        model.addAttribute("seatMap", seatMap);
        model.addAttribute("onCode", onCode);

        return "seat/seat_input";

    }


    @PostMapping("/confirm")
    public String confirmReserveInfo(@ModelAttribute("form") SeatForm form, Model model, HttpSession session){
        
        //セッション情報取得
        SeatForm sessionForm = (SeatForm)session.getAttribute("sessionForm");

        //選択された座席を予約できるかチェック
        if(!movieService.checkCanReserveSeat(sessionForm.getOnCode(), form.getSeats())){
            model.addAttribute("message", "予約できない席が含まれています");
            //選択可能な席をリフレッシュ
            Map<String, List<Seat>> seatMap = movieService.makeSeatMap(sessionForm.getOnCode(), sessionForm.getScCode());
            model.addAttribute("seatMap", seatMap);
            return "seat/seat_input";
        }
        
        model.addAttribute("selectedSeats", form);
        //セッションformに保存
        sessionForm.setSeats(form.getSeats());
        session.setAttribute("sessionForm", sessionForm);
        return "seat/seat_confirm";

    }


    @PostMapping("/regist")
    public String registReserveInfo(@ModelAttribute("form") SeatForm form, Model model, HttpSession session){
        
        //セッション情報取得
        SeatForm sessionForm = (SeatForm)session.getAttribute("sessionForm");

        //選択された座席を予約できるかチェック
        if(!movieService.checkCanReserveSeat(sessionForm.getOnCode(), sessionForm.getSeats())){

            //TODO　予約不可ページへ
            return "seat/seat_input";
        }

        //予約情報の登録を行う
        List<Reserve> resvList= reserveService.makeReserveList(sessionForm.getOnCode(), sessionForm.getSeats());
        reserveService.registerReserve(resvList);
        model.addAttribute("message", "登録に成功しました");


        return "seat/seat_finish";

    }


}
