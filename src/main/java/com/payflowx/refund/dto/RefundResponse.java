package com.payflowx.refund.dto;

import com.payflowx.refund.enums.RefundStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefundResponse {

    public Long refundAmount;

    public RefundStatus status;

}
