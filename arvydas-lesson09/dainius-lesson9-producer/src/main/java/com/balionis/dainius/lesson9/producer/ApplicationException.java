package com.balionis.dainius.lesson9.producer;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String message, Throwable root) {
        super(message, root);
    }
}