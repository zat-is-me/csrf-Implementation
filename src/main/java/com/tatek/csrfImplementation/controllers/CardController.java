package com.tatek.csrfImplementation.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CardController {

    @GetMapping("/myCards")
    public String myAccount(){
        return "Cards Details: ";
    }
}
