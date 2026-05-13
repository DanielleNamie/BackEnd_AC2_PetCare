package com.facens.petcare.exception;

/**
 * Exceção lançada quando um recurso solicitado não é encontrado no banco de dados.
 * Resulta em resposta HTTP 404 Not Found via GlobalExceptionHandler.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
