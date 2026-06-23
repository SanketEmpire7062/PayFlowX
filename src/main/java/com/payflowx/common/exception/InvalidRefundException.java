package com.payflowx.common.exception;

import org.hibernate.boot.model.internal.StrictIdGeneratorResolverSecondPass;

public class InvalidRefundException extends RuntimeException{

    public String message;

    public InvalidRefundException(String message){
        super(message);
    }

}
