package com.facens.petcare.exception;

/**
 * Exceção lançada quando uma regra de negócio é violada.
 * Resulta em resposta HTTP 400 Bad Request via GlobalExceptionHandler.
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
