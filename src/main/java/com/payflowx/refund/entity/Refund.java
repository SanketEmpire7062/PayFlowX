package com.payflowx.refund.entity;


import com.payflowx.refund.enums.RefundStatus;
import com.payflowx.transaction.entity.Transaction;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "refund")
@Data
public class Refund {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refund_id")
    @Id
    private Long refundId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    private Long amount;

    @Column(name = "refund_status", nullable = false)
    private RefundStatus refundStatus;

    @Column(name = "refund_date", updatable = false)
    @CreationTimestamp
    private LocalDateTime refundDate;

}
