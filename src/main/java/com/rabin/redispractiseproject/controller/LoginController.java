package com.rabin.redispractiseproject.controller;

import com.rabin.redispractiseproject.dto.CustomerDto;
import com.rabin.redispractiseproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
public class LoginController {

    @Autowired
    private CustomerService customerService;


    @PostMapping
    public ResponseEntity<String> registeringCustomerInfo(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.savingTheRecordOfCustomerInfo(customerDto));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getListOfRecord() {
        return ResponseEntity.ok(customerService.listOfRecordFromDataBase());
    }



    @RequestMapping("/user")
    public CustomerDto getUserDetailsAfterLogin(Authentication authentication) {
     return customerService.getUserDetailsAfterLogin(authentication);

    }

}
