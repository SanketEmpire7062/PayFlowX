package com.payflowx.login.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class Login {


    private String email;

    private String password;

    private String jwtToken;
}
