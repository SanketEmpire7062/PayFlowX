package com.payflowx.payment.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    private long amount;

    @Column(name = "bill_number")
    private long billNumber;


    @Column(name = "payment_status")
    private String paymentStatus;


    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updateTime")
    private LocalDateTime updatedAt;
}
