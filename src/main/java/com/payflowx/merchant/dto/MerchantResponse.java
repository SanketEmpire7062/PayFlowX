package com.payflowx.merchant.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MerchantResponse {

     String merchantName;

     String apiKey;

     Boolean status;

     String messege;
}
