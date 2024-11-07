package com.jackson.exception;

public class PhoneExistException extends BaseException{
    public PhoneExistException(){}
    public PhoneExistException(String msg){
        super(msg);
    }
}
