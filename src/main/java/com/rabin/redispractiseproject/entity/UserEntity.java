package com.rabin.redispractiseproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user_tbl")
public class UserEntity   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id" , nullable = false)
    private Long userId;
    @Column(name = "user_name" , nullable = false)
    private String name;
    @Column(name = "user_email" , nullable = false)
    private String email;
    @Column(name = "user_password" , nullable = false)
    private String password;
    @Column(name = "user_address" , nullable = false)
    private String address;
    @Column(name = "user_role" , nullable = false)
    private String role;
}
