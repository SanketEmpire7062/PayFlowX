package com.payflowx.transaction.controller;


import com.payflowx.transaction.dto.TransactionRequest;
import com.payflowx.transaction.dto.TransactionResponse;
import com.payflowx.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @PostMapping("/initiate")
    public ResponseEntity<TransactionResponse> initiatePayment(
            @RequestHeader("X-API-KEY") String apiKey,
            @RequestBody TransactionRequest transactionRequest){


        return ResponseEntity.ok(
                transactionService.initiateTransaction(
                        transactionRequest,
                        apiKey)
        );




        }


    @GetMapping("/{referenceId}")
    ResponseEntity<TransactionResponse> getPaymentDetails(@PathVariable String referenceId){

        return  ResponseEntity.ok(transactionService.getTransactionDetails(referenceId));


    }
}
