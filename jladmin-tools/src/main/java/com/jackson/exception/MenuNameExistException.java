package com.jackson.exception;

public class MenuNameExistException extends BaseException {
    public MenuNameExistException() {
    }

    public MenuNameExistException(String msg) {
        super(msg);
    }
}
