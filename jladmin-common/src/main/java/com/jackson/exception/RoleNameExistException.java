package com.jackson.exception;

public class RoleNameExistException extends BaseException{
    public RoleNameExistException(){}
    public RoleNameExistException(String msg){
        super(msg);
    }
}
