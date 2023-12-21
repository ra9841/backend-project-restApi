package com.rabin.redispractiseproject.service;

import com.rabin.redispractiseproject.dto.UserRequest;
import com.rabin.redispractiseproject.dto.UserResponse;

import java.util.List;

public interface UserService {
    String savingTheRecord(UserRequest userRequest);

    List<UserResponse> listOfRecord();

    UserRequest getRecordByName(String name);
}
