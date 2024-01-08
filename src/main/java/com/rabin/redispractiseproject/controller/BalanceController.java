package com.rabin.redispractiseproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myBalance")
public class BalanceController {

    @GetMapping
    public String getBalance() {
        return "this is my balance";
    }
}
