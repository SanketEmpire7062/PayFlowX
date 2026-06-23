package com.payflowx.refund.controller;

import com.payflowx.refund.dto.RefundRequest;
import com.payflowx.refund.dto.RefundResponse;
import com.payflowx.refund.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/refund")
public class RefundController {


    @Autowired
    RefundService refundService;



    @PostMapping("/initiate")
    public ResponseEntity<RefundResponse> refundResponse(@RequestBody RefundRequest refundRequest){

        return new ResponseEntity<>(refundService.refundTransaction(refundRequest), HttpStatus.CREATED);
    }
}
