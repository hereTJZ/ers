package com.example.ers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class helloController {
    @RequestMapping("/index")
    public String sayHello(Model model) {
        model.addAttribute("name", "tangtang");
        return "index";
    }
}
