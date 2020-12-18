package com.redeveight.covid.controllers;

import com.redeveight.covid.models.LocationStats;
import com.redeveight.covid.services.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CovidDataService covidDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> stats = covidDataService.getStats();
        model.addAttribute("locationStats", stats);

        return "home";
    }
}
