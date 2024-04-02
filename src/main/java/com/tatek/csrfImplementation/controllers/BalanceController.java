package com.tatek.csrfImplementation.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BalanceController {

    @GetMapping("/myBalance")
    public String myAccount(){
        return "Balance details: ";
    }
}
