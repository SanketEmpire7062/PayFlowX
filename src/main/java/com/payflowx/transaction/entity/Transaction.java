package com.payflowx.transaction.entity;


import com.payflowx.transaction.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    @Column(name = "merchant_id")
    private long merchantId;

    private long amount;

    @Column(name = "bill_number")
    private String currency;



    @Column(name = "payment_method")
    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;


    @Column(name = "reference_id", unique = true)
    private String referenceId;


    @CreationTimestamp
    @Column(name = "payment_date")
    private LocalDateTime createdAt;
}
