package com.jackson.exception;

public class EmailExistException extends BaseException{
    public EmailExistException(){}
    public EmailExistException(String msg){
        super(msg);
    }
}
