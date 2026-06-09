package com.payflowx.payment.dto;

import lombok.Data;

@Data
public class PaymentResponse {

    private long transactionId;

    private String status;

    private  long referenceId;
}
