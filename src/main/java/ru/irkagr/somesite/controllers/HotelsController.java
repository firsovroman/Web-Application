package ru.irkagr.somesite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.irkagr.somesite.statistics.ApplicationStatistic;


@Controller
public class HotelsController {

    @Autowired
    ApplicationStatistic applicationStatistic;

    @GetMapping("/hotels")
    public String main(Model model){

        applicationStatistic.getCounterHotels().increment();

        return "unable";
    }

}
