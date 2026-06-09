package com.payflowx.merchant.service;

import com.payflowx.auth.dto.LoginResponse;
import com.payflowx.merchant.dto.MerchantRequest;
import com.payflowx.merchant.dto.MerchantResponse;
import com.payflowx.merchant.entity.Merchant;
import com.payflowx.merchant.repository.MerchantRepository;
import com.payflowx.merchant.util.ApiKeyGenerator;
import com.payflowx.merchant.util.HashUtil;
import com.payflowx.user.entity.User;
import com.payflowx.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private UserRepository userRepository;

    public MerchantResponse createMerchant(MerchantRequest merchantRequest){

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User loggedInUser = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        String rawApiKey = ApiKeyGenerator.generateKey();

        String hashApiKey = HashUtil.sha256(rawApiKey);

        Merchant merchant = new Merchant();
        merchant.setMerchantName(merchantRequest.getMerchantName());
        merchant.setApiKey(hashApiKey);
        merchant.setStatus(true);
        merchant.setUser(loggedInUser);



        Merchant saveMerchant = merchantRepository.save(merchant);

        return MerchantResponse.builder()
                .merchantName(saveMerchant.getMerchantName())
                .apiKey(rawApiKey)
                .status(saveMerchant.getStatus())
                .messege("Welcome " + saveMerchant.getMerchantName()
                        + ", you are onboarded successfully")
                .build();
    }
}
