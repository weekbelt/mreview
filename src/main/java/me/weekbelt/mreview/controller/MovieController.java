package me.weekbelt.mreview.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/movie")
public class MovieController {

    @GetMapping("/register")
    public void register() {

    }
}
