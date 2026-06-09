package com.payflowx.merchant.entity;


import com.payflowx.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "merchant")
@Data
public class Merchant {


    @Id
    @Column(name = "merchant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long merchantId;

    @Column(name = "merchant_name")
    private String merchantName;

    private Boolean status = false;

    @Column(name = "creation_date", updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;


    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    private String webhookUrl;


    @Column(unique = true, nullable = false)
    private String apiKey;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
