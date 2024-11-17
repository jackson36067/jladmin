package com.jackson.exception;

public class JobNameExistException extends BaseException {
    public JobNameExistException() {
    }

    public JobNameExistException(String msg) {
        super(msg);
    }
}
