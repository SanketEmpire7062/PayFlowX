package com.payflowx.admin.controller;

import com.payflowx.admin.service.AdminService;
import com.payflowx.merchant.entity.Merchant;
import com.payflowx.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminDashboard {

    @Autowired
    AdminService adminService;



    @GetMapping("/showuser")
  public ResponseEntity<?> getAllRegisteredUser(){

      List<User> user = adminService.getAllRegisteredUser();



      if(user.isEmpty()){
          return ResponseEntity
                  .status(HttpStatus.NOT_FOUND)
                  .body("No user registered yet");
      }

      return ResponseEntity.ok(user);
  }

  @GetMapping("/showmerchant")
  public ResponseEntity<?> getAllOnboardedMerchant(){

        List<Merchant> merchants = adminService.getAllOnboardedMerchant();

      if(merchants.isEmpty()){
          return ResponseEntity
                  .status(HttpStatus.NOT_FOUND)
                  .body("No user registered yet");
      }

      return ResponseEntity.ok(merchants);
  }
}
