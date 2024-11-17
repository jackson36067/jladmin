package com.jackson.exception;

public class UserNotExistException extends BaseException {
    public UserNotExistException() {
    }

    public UserNotExistException(String msg) {
        super(msg);
    }
}
