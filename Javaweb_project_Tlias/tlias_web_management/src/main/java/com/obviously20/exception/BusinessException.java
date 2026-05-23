package com.obviously20.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String msg){
        super(msg);
    }
}
