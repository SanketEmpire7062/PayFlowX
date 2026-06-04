package com.payflowx.auth.service;

import com.payflowx.auth.dto.LoginRequest;
import com.payflowx.auth.dto.LoginResponse;
import com.payflowx.auth.repository.AuthRepository;
import com.payflowx.common.exception.InvalidCredentialsException;
import com.payflowx.common.exception.UserNotFoundException;
import com.payflowx.security.JwtService;
import com.payflowx.user.entity.User;
import com.payflowx.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public LoginResponse loginSuccess(LoginRequest loginRequest){

        BCryptPasswordEncoder bCryptPasswordDecoder = new BCryptPasswordEncoder();

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with email: " + loginRequest.getEmail()));

        if(!bCryptPasswordDecoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Invalid email or password");
        }


        if(!userRepository.existsByEmail(loginRequest.getEmail())){
            System.out.println("User not present");
        }


        String token = jwtService.generateToken(loginRequest.getEmail());

        System.out.println("generated token is ::" + token);


        return LoginResponse.builder()
                .messege("Welcome " + user.getName())
                .token(token)
                .build();

    }

}
