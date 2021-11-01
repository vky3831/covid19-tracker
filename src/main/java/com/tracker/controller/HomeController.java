package com.tracker.controller;

import com.tracker.model.LocationStats;
import com.tracker.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private DataService dataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = dataService.getAllStats();
        int globalTotal = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int newCasesTotal = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("stats", allStats);
        model.addAttribute("globalTotal", globalTotal);
        model.addAttribute("newCasesTotal", newCasesTotal);
        return "home";
    }
}
