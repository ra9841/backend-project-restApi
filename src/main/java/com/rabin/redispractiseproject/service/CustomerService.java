package com.rabin.redispractiseproject.service;

import com.rabin.redispractiseproject.dto.CustomerDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CustomerService {
    String savingTheRecordOfCustomerInfo(CustomerDto customerDto);

    List<CustomerDto> listOfRecordFromDataBase();

    CustomerDto getUserDetailsAfterLogin(Authentication authentication);
}
