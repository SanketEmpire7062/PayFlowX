package com.payflowx.user.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class UserResponse {


    private Integer userId;
    private String name;
    private String email;





}
