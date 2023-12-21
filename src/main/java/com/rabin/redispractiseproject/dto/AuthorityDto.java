package com.rabin.redispractiseproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorityDto {
    private Long id;
    private String name;
    private CustomerDto customerDto;
}
