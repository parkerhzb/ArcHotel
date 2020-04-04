package com.arcsoft.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test2Controller {

    @RequestMapping("/testpage")
    public String testpage() {
        return "upload";
    }
}
