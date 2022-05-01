package com.example.ers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/refresh")
public class RefreshController {

    @GetMapping("/showpr")
    public String showPR(Model model) {
        model.addAttribute("msg", "初始内容");
        return "partialRefreshTest";
    }

    @RequestMapping("/pr")
    public String partialRefresh(Model model) {
        model.addAttribute("msg", "异步请求刷新后的内容");
        return "partialRefreshTest::visit";
    }

}