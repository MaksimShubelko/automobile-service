package com.example.automobileservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @RequestMapping(value = {"", "/", "index", "index.html"})
    public String index() {
        return "index";
    }
}
