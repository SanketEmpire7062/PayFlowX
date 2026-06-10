package com.payflowx.webhook.dto;

import com.payflowx.transaction.enums.TransactionStatus;
import lombok.Data;

@Data
public class WebhookRequest {

    private String referenceId;

    private long amount;

    private TransactionStatus status;
}
