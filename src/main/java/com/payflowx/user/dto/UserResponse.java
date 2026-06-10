package com.payflowx.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {


    public Long userId;
    public String name;
    public String email;
    public String message;





}
