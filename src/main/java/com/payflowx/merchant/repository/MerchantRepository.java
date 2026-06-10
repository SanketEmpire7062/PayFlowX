package com.payflowx.merchant.repository;


import com.payflowx.merchant.entity.Merchant;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {

    Optional<Merchant> findByApiKey(String apiKey);

}
