package com.payflowx.payment.dto;

import lombok.Data;

@Data
public class PaymentRequest {

    private Long amount;
    private String currency;
    private String paymentMethod;
}
