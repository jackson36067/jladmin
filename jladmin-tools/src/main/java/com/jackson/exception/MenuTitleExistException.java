package com.jackson.exception;

public class MenuTitleExistException extends BaseException {
    public MenuTitleExistException() {
    }

    public MenuTitleExistException(String msg) {
        super(msg);
    }
}
