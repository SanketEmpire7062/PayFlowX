package com.payflowx.user.service;

import com.payflowx.common.exception.BadRequestException;
import com.payflowx.transaction.dto.TransactionResponse;
import com.payflowx.user.dto.UserRequest;
import com.payflowx.user.dto.UserResponse;
import com.payflowx.user.entity.User;
import com.payflowx.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserResponse saveUser(UserRequest  userRequest){

        if(userRepository.existsByEmail(userRequest.getEmail())){
            throw new BadRequestException("User already exists");
        }


        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String password =  bCryptPasswordEncoder.encode(userRequest.getPassword());
        System.out.println("encrypted password is ::" +  password);

        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(password);


        User savedUser = userRepository.save(user);

       /* UserResponse response = new UserResponse();
        response.setEmail(savedUser.getEmail());
        response.setUserId(savedUser.getUserId());
        response.setName(savedUser.getName());*/

        return UserResponse.builder()
                .message("account created successfully")
                .userId(savedUser.getUserId())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .build();
    }

}
