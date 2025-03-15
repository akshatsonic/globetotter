package com.akshatsonic.globetotter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PingController {

    @RequestMapping("/ping")
    public String ping() {
        return "Pong";
    }
}
