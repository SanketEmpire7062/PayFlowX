package com.payflowx.transaction.dto;

import com.payflowx.transaction.enums.TransactionStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {

    private long transactionId;

    private TransactionStatus status;

    private  String referenceId;
}
