package com.payflowx.auth.controller;


import com.payflowx.auth.dto.LoginRequest;
import com.payflowx.auth.dto.LoginResponse;
import com.payflowx.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loggedInUser(@RequestBody LoginRequest loginRequest){

        LoginResponse loginResponse = authService.loginSuccess(loginRequest);

        return  new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }





}
