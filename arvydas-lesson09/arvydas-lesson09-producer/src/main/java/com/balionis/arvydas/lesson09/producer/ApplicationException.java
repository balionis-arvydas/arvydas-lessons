package com.balionis.arvydas.lesson09.producer;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String message, Throwable root) {
        super(message, root);
    }
}