package com.payflowx.refund.service;

import com.payflowx.common.exception.InvalidRefundException;
import com.payflowx.common.exception.RefundProcessingException;
import com.payflowx.common.exception.ResourceNotFoundException;
import com.payflowx.refund.dto.RefundRequest;
import com.payflowx.refund.dto.RefundResponse;
import com.payflowx.refund.entity.Refund;
import com.payflowx.refund.enums.RefundStatus;
import com.payflowx.refund.repository.RefundRepository;
import com.payflowx.transaction.entity.Transaction;
import com.payflowx.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefundService {

    @Autowired
    private RefundRepository refundRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public RefundResponse refundTransaction(RefundRequest refundRequest){

        Transaction transaction = transactionRepository.findById(refundRequest.getTransactionId())
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not exists"));



        if(refundRequest.getAmount() > transaction.getAmount()){
            throw new InvalidRefundException("Refund amount is exceeds original transaction amount");
        }

        Refund refund = new Refund();
        refund.setTransaction(transaction);
        refund.setAmount(refundRequest.getAmount());
        refund.setRefundStatus(RefundStatus.INITIATED);

        try{
            refund.setRefundStatus(RefundStatus.PROCESSING);

            refundRepository.save(refund);

            long updatedAmount = transaction.getAmount() - refundRequest.getAmount();
            transaction.setAmount(updatedAmount);

            if (updatedAmount == 0) {
                refund.setRefundStatus(RefundStatus.SUCCESS);

            }else{
                refund.setRefundStatus(RefundStatus.PARTIALLY_REFUNDED);
            }

            transactionRepository.save(transaction);
        }
        catch (Exception e){
            refund.setRefundStatus(RefundStatus.FAILED);
            refundRepository.save(refund);
            e.printStackTrace();
            throw new RefundProcessingException("refund processing failed");


        }


        return RefundResponse.builder()
                .refundAmount(refund.getAmount())
                .status(refund.getRefundStatus())
                .build();

    }
}
