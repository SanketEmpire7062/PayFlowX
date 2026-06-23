package com.payflowx.refund.dto;


import lombok.Data;

@Data
public class RefundRequest {

    public String transactionId;

    public Long amount;

}
