package com.rabin.redispractiseproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDto {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private String pwd;
    private Date createDt;
    private Set<AuthorityDto> authorityDtos;

}
