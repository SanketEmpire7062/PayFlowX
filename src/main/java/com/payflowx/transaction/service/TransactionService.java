package com.payflowx.transaction.service;


import com.payflowx.common.exception.BadRequestException;
import com.payflowx.merchant.entity.Merchant;
import com.payflowx.merchant.repository.MerchantRepository;
import com.payflowx.merchant.util.HashUtil;
import com.payflowx.transaction.dto.TransactionRequest;
import com.payflowx.transaction.dto.TransactionResponse;
import com.payflowx.transaction.entity.Transaction;
import com.payflowx.transaction.enums.TransactionStatus;
import com.payflowx.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Async
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MerchantRepository merchantRepository;

 /*   @Autowired
    private WebhookService webhookService;*/

    @Autowired
    private TransactionProcessorService transactionProcessorService;

    private  TransactionStatus finalStatus;

     public TransactionResponse initiateTransaction(TransactionRequest transactionRequest, String apiKey){

         String hashApiKey = HashUtil.sha256(apiKey);


         // find merchant
         Merchant merchant = merchantRepository
                 .findByApiKey(hashApiKey)
                 .orElseThrow(()->
                         new BadRequestException("Invalid api key"));

     /*    // validate amount
         if(transactionRequest.getAmount() <= 0){
             throw new BadRequestException("amount must be greater than 0");
         }*/

         // initiate transaction

         Transaction transaction = new Transaction();
         transaction.setStatus(TransactionStatus.INITIATED);
         transaction.setMerchantId(merchant.getMerchantId());
         transaction.setAmount(transactionRequest.getAmount());
         transaction.setCurrency(transactionRequest.getCurrency());
         transaction.setPaymentMethod(transactionRequest.getPaymentMethod());

         transaction.setStatus(TransactionStatus.PROCESSING);
         transaction.setReferenceId(generateTransactionReferenceId());
         transactionRepository.save(transaction);

         transactionProcessorService.processTransaction(transaction, merchant);

/*

         transaction.setStatus(TransactionStatus.INITIATED);

         transaction.setReferenceId(generateTransactionReferenceId());

         transactionRepository.save(transaction);

         if (transactionRequest.getAmount() <= 0) {
             transaction.setStatus(TransactionStatus.FAILED);
             transactionRepository.save(transaction);

             return TransactionResponse.builder()
                     .referenceId(transaction.getReferenceId())
                     .status(transaction.getStatus())
                     .build();
         }


         // stimulate processing started
         transaction.setStatus(TransactionStatus.PROCESSING);

         transactionRepository.save(transaction);

         if(transactionRequest.getAmount() > 0){

             finalStatus = TransactionStatus.SUCCESS;
         }
         else{
             finalStatus = TransactionStatus.FAILED;


         }

         transaction.setStatus(finalStatus);

         try{
             Thread.sleep(30_000);

             transactionRepository.save(transaction);
         }catch (Exception e){
             e.printStackTrace();
         }
*/


        // webhookService.sendWebhook(transaction, merchant);


         return TransactionResponse.builder()
                 .referenceId(transaction.getReferenceId())
                 .status(transaction.getStatus())
                 .build();
     }

     public String generateTransactionReferenceId(){
         return "TXN" + System.currentTimeMillis();
     }


}
