package com.sideproject.saolife.Exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String msg) {
        super(msg);
    }
}
