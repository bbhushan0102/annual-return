package com.ars.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @ModelAttribute("progressStatus")
    public Map<String, String> progressStatus() {
        // Initialize progressStatus with default values if it doesn't exist
        Map<String, String> progressStatus = new HashMap<>();
        progressStatus.put("incomeSpending", "notstarted");
        progressStatus.put("governmentContract", "notstarted");
        progressStatus.put("governmentContractValue", "notstarted");
        return progressStatus;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
