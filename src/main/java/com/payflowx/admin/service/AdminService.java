package com.payflowx.admin.service;


import com.payflowx.merchant.entity.Merchant;
import com.payflowx.merchant.repository.MerchantRepository;
import com.payflowx.user.entity.User;
import com.payflowx.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;


    @Autowired
    MerchantRepository merchantRepository;

    public List<User> getAllRegisteredUser(){
        return userRepository.findAll();
    }

    public  List<Merchant> getAllOnboardedMerchant(){
        return merchantRepository.findAll();
    }


}
