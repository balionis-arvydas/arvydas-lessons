package com.balionis.arvydas.lesson08.producer;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String message, Throwable root) {
        super(message, root);
    }
}