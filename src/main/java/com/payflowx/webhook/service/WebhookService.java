package com.payflowx.webhook.service;


import com.payflowx.merchant.entity.Merchant;
import com.payflowx.transaction.entity.Transaction;
import com.payflowx.webhook.dto.WebhookRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    /*private RestTemplate restTemplate = new RestTemplate();


    @Async
    public void sendWebhook(Transaction transaction, Merchant merchant){
        try{

            WebhookRequest request = new WebhookRequest();


            request.setReferenceId(transaction.getReferenceId());

            request.setAmount(transaction.getAmount());

            request.setStatus(transaction.getStatus());

            restTemplate.postForObject(
                    merchant.getWebhookUrl(),
                    request,
                    String.class
            );

        }catch (Exception e){
            System.out.println(
                    "Webhook failed for transaction "
                            + transaction.getReferenceId()
            );

            e.printStackTrace();
        }


    }*/
}
