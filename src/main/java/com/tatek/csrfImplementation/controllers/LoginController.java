package com.tatek.csrfImplementation.controllers;


import com.tatek.csrfImplementation.entities.Customer;
import com.tatek.csrfImplementation.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<String> login(@RequestBody Customer customer){
        Customer savedCustomer = null;
        ResponseEntity responseEntity =null;

        try {
            String hashPwd = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(hashPwd);
            savedCustomer = customerRepository.save(customer);

            if (savedCustomer.getId()>0){
                responseEntity = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        }catch (Exception exception){
            responseEntity = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception due to: "+ exception.getMessage());
        }
        return responseEntity;
    }

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication){
        List<Customer> customers = customerRepository.findByEmail(authentication.getName());
        if (!customers.isEmpty())
            return customers.get(0);
        else
            return null;
    }
}
