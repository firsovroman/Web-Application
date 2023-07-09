package ru.irkagr.somesite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.irkagr.somesite.statistics.ApplicationStatistic;

@Controller
public class ForumController {

    @Autowired
    ApplicationStatistic applicationStatistic;

    @GetMapping("/forum")
    public String main(Model model){

        applicationStatistic.getCounterForum().increment();

        return "tempUnable";
    }


}
