package com.payflowx.transaction.service;


import com.payflowx.common.exception.BadRequestException;
import com.payflowx.common.exception.InvalidAmountException;
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

         if(transactionRequest.getAmount() <= 0){
             throw new InvalidAmountException("transaction amount must not be 0");
         }


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



         return TransactionResponse.builder()
                 .referenceId(transaction.getReferenceId())
                 .transactionId(transaction.getTransactionId())
                 .amount(transaction.getAmount())
                 .status(transaction.getStatus())

                 .build();
     }

     public String generateTransactionReferenceId(){
         return "TXN" + System.currentTimeMillis();
     }


     public TransactionResponse getTransactionDetails(String referenceId){

         Transaction transaction = transactionRepository
                 .findByReferenceId(referenceId)
                 .orElseThrow(() ->
                         new BadRequestException("Transaction not found"));

         return TransactionResponse.builder()
                 .transactionId(transaction.getTransactionId())
                 .referenceId(transaction.getReferenceId())
                 .status(transaction.getStatus())
                 .build();


     }




}
