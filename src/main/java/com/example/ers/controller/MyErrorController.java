package com.example.ers.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {
    /**
     * 错误页面重定向
     */
    @RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "error";
    }
}
