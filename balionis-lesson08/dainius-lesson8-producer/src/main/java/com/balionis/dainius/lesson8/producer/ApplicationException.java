package com.balionis.dainius.lesson8.producer;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String message, Throwable root) {
        super(message, root);
    }
}