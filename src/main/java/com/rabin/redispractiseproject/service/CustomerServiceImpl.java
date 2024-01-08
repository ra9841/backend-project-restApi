package com.rabin.redispractiseproject.service;

import com.rabin.redispractiseproject.dto.CustomerDto;
import com.rabin.redispractiseproject.entity.Customer;
import com.rabin.redispractiseproject.exception.*;
import com.rabin.redispractiseproject.exception.InternalError;
import com.rabin.redispractiseproject.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String savingTheRecordOfCustomerInfo(CustomerDto customerDto) {
        try {
            // Check if the provided email is valid
            if (!isValidEmail(customerDto.getEmail())) {
                throw new InvalidEmailException("Invalid email address");
            }

            // Validate phone number
            if (!isValidPhoneNumber(customerDto.getPhoneNumber())) {
                throw new InvalidPhoneNumberException("Invalid phone number format. Use the pattern: 000-000-0000");
            }

            // Validate password strength
            validatePasswordStrength(customerDto.getPwd());

            // Convert email to lowercase for case-insensitive check
            String lowercaseEmail = customerDto.getEmail().toLowerCase();

            // Validate other customer information,
            validateCustomerInfo(customerDto);

            Optional<Customer> existCustomer = customerRepository.findByEmail(lowercaseEmail);

            if (existCustomer.isPresent()) {
                throw new UserAlreadyExistException("Email address is already present in the database");
            }

            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDto, customer);

            customer.setEmail(lowercaseEmail); // Set the lowercase email in the customer object
            customer.setPhoneNumber(customerDto.getPhoneNumber()); // Set the phone number
            customer.setPwd(passwordEncoder.encode(customerDto.getPwd()));
            customer.setCreateDt(new Date());

            // Apply business logic to determine the role based on age
            int age = calculateAge(customerDto.getBirthDate());
            customer.setRole(determineRoleBasedOnAge(age));

            customerRepository.save(customer);

            // Log success or return a more detailed success message if needed
            log.info("Registration successful for email: {}", customer.getEmail());
            return "Registration successful";
        } catch (InvalidEmailException | InvalidPhoneNumberException | InvalidPasswordException | InvalidCustomerInfoException e) {
            // Log the exception or handle it as needed
            log.error("Invalid customer information: {}", e.getMessage());
            throw e;
        } catch (UserAlreadyExistException e) {
            // Log the exception or handle it as needed
            log.error("User already exists with email: {}", customerDto.getEmail());
            throw e;
        } catch (Exception e) {
            // Log the exception or handle it as needed
            log.error("Internal server error while processing registration", e);
            throw new InternalError("Internal server error");
        }
    }



    // Add a method to calculate age based on the birth date
    private int calculateAge(Date birthDate) {
        // Implementing logic to calculate the age based on the birth date
        Calendar today = Calendar.getInstance();
        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(birthDate);

        int age = today.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);

        // Adjust age if the birthday hasn't occurred yet this year
        if (today.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }

    // Add a method to determine the role based on age
    private String determineRoleBasedOnAge(int age) {
        // Implement your business logic to determine the role based on the age
        // For example, if the customer is 18 or older, assign the role "ADULT"
        // Otherwise, assign the role "MINOR"
        return (age >= 18) ? "ADULT" : "MINOR";
    }

    // Add a method to validate the email format
    private boolean isValidEmail(String email) {
        // Implement your email validation logic, e.g., using regular expressions
        // For a simple example, checking if it contains "@" and "." can be done
        return email != null && email.contains("@") && email.contains(".");
    }

    // Add a method to validate the phone number format
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Implement your phone number validation logic, e.g., using regular expressions
        // For the specified pattern 123-123-2323, you can use the following regex:
        return phoneNumber != null && phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}");
    }

    // Add a method to validate other customer information
    private void validateCustomerInfo(CustomerDto customerDto) {
        // Implement your validation logic for other customer information, e.g., name validation, etc.
        // Throw an exception if the information is invalid
        if (customerDto.getName() == null || customerDto.getName().isEmpty()) {
            throw new InvalidCustomerInfoException("Customer name is required");
        }
        // Add more validation checks as needed
    }

    // Add a method to validate password strength
    private void validatePasswordStrength(String password) {
        // Password strength rules
        // Example rules:
        // - Easy: Minimum 6 characters
        // - Normal: Minimum 8 characters, at least one special character
        // - Hard: Minimum 10 characters, at least one special character, one uppercase letter, and one digit

        if (password.length() < 6) {
            throw new InvalidPasswordException("Password must be at least 6 characters long (easy)");
        }

        if (password.length() < 8 || !password.matches(".*[!@#$%^&*()-_=+{};:,<.>/?].*")) {
            throw new InvalidPasswordException("Password must be at least 8 characters long and contain at least one special character (normal)");
        }

        if (password.length() < 10 || !password.matches(".*[!@#$%^&*()-_=+{};:,<.>/?].*") || !password.matches(".*[A-Z].*") || !password.matches(".*\\d.*")) {
            throw new InvalidPasswordException("Password must be at least 10 characters long, contain at least one special character, one uppercase letter, and one digit (hard)");
        }
    }


    @Override
    public List<CustomerDto> listOfRecordFromDataBase() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> {
            CustomerDto customerDto = new CustomerDto();
            BeanUtils.copyProperties(customer, customerDto);
            return customerDto;
        }).toList();
    }

    @Override
    public CustomerDto getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> existCustomer = customerRepository.findByEmail(authentication.getName());
        if (existCustomer.isPresent()) {
            Customer customer = existCustomer.get();
            CustomerDto customerDto = new CustomerDto();
            BeanUtils.copyProperties(customer, customerDto);
            return customerDto;
        } else {
            return null;
        }
    }





}
