package com.rabin.redispractiseproject.controller;

import com.rabin.redispractiseproject.dto.CustomerDto;
import com.rabin.redispractiseproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class LoginController {

    @Autowired
    private CustomerService customerService;


    @PostMapping
    public ResponseEntity<String> registeringCustomerInfo(@RequestBody CustomerDto customerDto){
        return ResponseEntity.status(HttpStatus.CREATED).body( customerService.savingTheRecordOfCustomerInfo(customerDto));
    }

}
