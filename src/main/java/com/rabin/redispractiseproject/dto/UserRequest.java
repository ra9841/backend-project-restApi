package com.rabin.redispractiseproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest implements Serializable {
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String address;


}
