package com.rabin.redispractiseproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customer_tbl")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native")
    @Column(name = "customer_id")
    private int id;
    @Column(name = "customer_name", nullable = false)
    private String name;
    @Column(name = "customer_email", nullable = false)
    private String email;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    private Date birthDate;
    private String role;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "customer_password", nullable = false)
    private String pwd;
    @Column(name = "create_dt")
    private Date createDt;
    @JsonIgnore
    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    private Set<Authority> authorities;
}
