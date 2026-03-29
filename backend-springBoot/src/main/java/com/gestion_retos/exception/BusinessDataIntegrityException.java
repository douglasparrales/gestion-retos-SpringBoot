package com.gestion_retos.exception;

public class BusinessDataIntegrityException extends RuntimeException{
    public BusinessDataIntegrityException(String message) {
            super(message);
    }
}
