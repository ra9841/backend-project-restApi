package com.rabin.redispractiseproject.controller;

import com.rabin.redispractiseproject.dto.UserRequest;
import com.rabin.redispractiseproject.dto.UserResponse;
import com.rabin.redispractiseproject.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
//@EnableCaching    //before running application run your redis server.Until & unless you do not close your redis server, data were saved in redis server otherwise put time limit on properties file
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> registeringTheRecord(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.savingTheRecord(userRequest));
    }

    @GetMapping
    public List<UserResponse> showingListOfRecord() {
        return userService.listOfRecord();
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserResponse> getParticularName(@PathVariable String name) {
        UserRequest userRequest = userService.getRecordByName(name);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(userRequest, userResponse);
        return ResponseEntity.ok(userResponse);
    }
}
