package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/secretaire")
public class PageSecretaireController {

    @GetMapping("/dashboard")
    public String dashboard() { return "pagesecretaire"; }
}