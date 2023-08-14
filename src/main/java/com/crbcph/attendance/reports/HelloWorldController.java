package com.crbcph.attendance.reports;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HelloWorldController {
    private final ApiConfig apiConfig;

    @GetMapping("/hello")
    public String hello(Model model) {
        System.out.println(apiConfig.getUrl());
        model.addAttribute("message", "Hello, Shiela Claudz!");
        return "hello";
    }
}