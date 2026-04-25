package com.example.demo.controller;

import com.example.demo.dto.DashboardData;
import com.example.demo.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class AuthController {

    private final DashboardService dashboardService;

    public AuthController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        DashboardData dashboardData = dashboardService.buildDashboard(LocalDate.now());
        model.addAttribute("dashboardData", dashboardData);
        return "dashboard";
    }
}