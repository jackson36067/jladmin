package com.jackson.exception;

public class UserHasLoginException extends BaseException{
    public UserHasLoginException(){}
    public UserHasLoginException(String msg){
        super(msg);
    }
}
