package com.sbedev.employeeapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @ResponseBody
    @RequestMapping("/")
    public String welcome() {
        return "Welcome to Employee API Rest !!!";
    }
}
