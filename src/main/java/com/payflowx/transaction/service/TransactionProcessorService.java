package com.payflowx.transaction.service;

import com.payflowx.merchant.entity.Merchant;
import com.payflowx.transaction.entity.Transaction;
import com.payflowx.transaction.enums.TransactionStatus;
import com.payflowx.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionProcessorService {

    @Autowired
    private TransactionRepository transactionRepository;

  /*  @Autowired
    private WebhookService webhookService;*/


   // @Async
    public  void processTransaction(Transaction transaction, Merchant merchant){
        try{

            Thread.sleep(2000);

            TransactionStatus finalStatus =
                    transaction.getAmount() > 0
                            ? TransactionStatus.SUCCESS
                            : TransactionStatus.FAILED;

            transaction.setStatus(finalStatus);
            transactionRepository.save(transaction);

      //      webhookService.sendWebhook(transaction, merchant);

        }catch (Exception e){
            e.printStackTrace();
        }
    }





}
