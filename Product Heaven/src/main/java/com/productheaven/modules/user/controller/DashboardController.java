package com.productheaven.modules.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping(value = {"/dashboard"})
    public String Index(Model model){
        model.addAttribute("title","Dashboard");
        return "dashboard";
    }
}
