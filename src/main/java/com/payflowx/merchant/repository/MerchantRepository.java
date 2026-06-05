package com.payflowx.merchant.repository;


import com.payflowx.merchant.entity.Merchant;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
}
