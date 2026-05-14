package com.facens.ac2.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> fields = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> fields.put(e.getField(), e.getDefaultMessage()));

        return build(HttpStatus.BAD_REQUEST, "Erro de validação",
                "Revise os campos enviados.", request.getRequestURI(), fields);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(
            ResourceNotFoundException ex, HttpServletRequest request) {

        return build(HttpStatus.NOT_FOUND, "Recurso não encontrado",
                ex.getMessage(), request.getRequestURI(), null);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError> handleBusiness(
            BusinessException ex, HttpServletRequest request) {

        return build(HttpStatus.BAD_REQUEST, "Regra de negócio violada",
                ex.getMessage(), request.getRequestURI(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnexpected(
            Exception ex, HttpServletRequest request) {

        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno",
                "Ocorreu um erro inesperado.", request.getRequestURI(), null);
    }

    private ResponseEntity<ApiError> build(
            HttpStatus status, String error, String message, String path,
            Map<String, String> fields) {

        ApiError apiError = new ApiError(
                LocalDateTime.now(), status.value(), error, message, path, fields);
        return ResponseEntity.status(status).body(apiError);
    }
}