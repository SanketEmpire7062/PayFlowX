package com.payflowx.payment.entity;


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
    private long currency;



    @Column(name = "payment_method")
    private String paymentMethod;

    private String status;


    @Column(name = "reference_id", unique = true)
    private long referenceId;


    @CreationTimestamp
    @Column(name = "payment_date")
    private LocalDateTime createdAt;
}
