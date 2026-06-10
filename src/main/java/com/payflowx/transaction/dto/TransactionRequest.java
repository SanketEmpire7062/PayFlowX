package com.payflowx.transaction.dto;

import lombok.Data;

@Data
public class TransactionRequest {

    private Long amount;
    private String currency;
    private String paymentMethod;
}
