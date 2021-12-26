package com.sideproject.saolife.Exception;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String msg) {
        super(msg);
    }
}
