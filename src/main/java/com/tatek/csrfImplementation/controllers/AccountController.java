package com.tatek.csrfImplementation.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @GetMapping("/myAccount")
    public String myAccount(){
        return "Account Details: ";
    }
}
