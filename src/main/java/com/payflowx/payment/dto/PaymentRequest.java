package com.payflowx.payment.dto;

import lombok.Data;

@Data
public class PaymentRequest {

    private Long amount;
    private long billNumber;
}
