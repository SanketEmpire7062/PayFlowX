package com.payflowx.merchant.controller;


import com.payflowx.auth.dto.LoginResponse;
import com.payflowx.merchant.dto.MerchantRequest;
import com.payflowx.merchant.dto.MerchantResponse;
import com.payflowx.merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @PostMapping("/create")
    public ResponseEntity<MerchantResponse> createMerchant(@RequestBody MerchantRequest merchantRequest) {

        MerchantResponse merchantResponse = merchantService.createMerchant(merchantRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(merchantResponse);
    }
}
