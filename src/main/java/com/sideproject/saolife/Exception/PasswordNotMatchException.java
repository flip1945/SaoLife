package com.sideproject.saolife.Exception;

public class PasswordNotMatchException extends RuntimeException {
    public PasswordNotMatchException(String msg) {
        super(msg);
    }
}
