package com.example.seat_reserve.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.seat_reserve.app.service.MovieService;
import com.example.seat_reserve.domain.movie.model.Movie;

@Controller
@RequestMapping("")
public class TopController {

    @Autowired
    private MovieService movieService;


    @GetMapping("/top")
    public String showTopMenu(Model model){

        //全ての上映情報を表示する
        List<Movie> movieList = movieService.findAllMovie();
        System.out.println(
            movieList
        );

        model.addAttribute("movieList", movieList);

        return "top_menu";
    
    }
    
}
