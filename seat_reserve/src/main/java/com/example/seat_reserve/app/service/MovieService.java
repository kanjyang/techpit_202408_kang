package com.example.seat_reserve.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.seat_reserve.domain.movie.model.Movie;
import com.example.seat_reserve.domain.movie.model.OnAir;
import com.example.seat_reserve.domain.movie.model.Seat;
import com.example.seat_reserve.domain.reserve.model.Reserve;

@Service
@Transactional
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReserveRepository reserveRepository;

    /**
     * 全ての映画の一覧を返却
     * @return
     */
    public List<Movie> findAllMovie(){
        return movieRepository.findAll();
    }

    /**
     * 上映中の映画の一覧を返却
     * @return
     */
    public List<OnAir> findAllOnAir(){
        return movieRepository.findAllOnAir();
    }

    /**
     * 選択した上映館の座席情報を取得
     * @return
     */
    public List<Seat> findSelectedScreenSeat(int scCode){
        return movieRepository.findSelectedScreenSeat(scCode);
    }



    /**
     * 座席情報の表示
     * @param scCode
     * @return
     */
    public Map<String, List<Seat>> makeSeatMap(int onCode, int scCode){

        //上映館の座席情報を取得
        List<Seat> seatList = this.findSelectedScreenSeat(scCode);
        
        //予約済みの座席は選択できないようにする
        List<Reserve> reserveList = this.findReservedSeats(onCode, null);
        List<String> reservedSeatNums = reserveList.stream().map(reserve -> reserve.getSeatNum()).collect(Collectors.toList());

        seatList = seatList.stream().map(reserve -> {
            if(!reservedSeatNums.contains(reserve.getSeatNum())){
                reserve.setSelected(true);
            }
            return reserve;
        }).collect(Collectors.toList());

        //座席表Map
        Map<String, List<Seat>> ret  = seatList.stream().collect(
            Collectors.groupingBy(Seat :: getSeatDivRom)
        );
        return ret;
    }

    /*
     * DB連携：予約情報の取得
     */
    public List<Reserve> findReservedSeats(int onCode, List<String> seats){

        return reserveRepository.findReservedSeats(onCode, seats);
    }

    /**
     * 予約可能な座席かチェック
     * @param seats
     * @return
     */
    public boolean checkCanReserveSeat(int onCode, List<String> seats){

       List<Reserve> ret = this.findReservedSeats(onCode,seats);

       if(ret!=null && ret.size()>0){
            return false;
       }else{
            return true;
       }
        
    }
}
