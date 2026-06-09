package com.payflowx.payment.repository;


import com.payflowx.payment.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Transaction, Long> {

}
