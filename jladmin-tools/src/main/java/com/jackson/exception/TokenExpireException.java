package com.jackson.exception;

public class TokenExpireException extends BaseException{
    public TokenExpireException(){}
    public TokenExpireException(String msg){
        super(msg);
    }
}
