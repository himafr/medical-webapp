package com.hema.medical_backend_spring.exception;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.hema.medical_backend_spring.api")
public class RestExceptionHandler {

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<Map<String, String>> incorrectPassword(IncorrectPasswordException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid email or password"));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, String>> userNotFound(UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> validation(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .toList();
        return ResponseEntity.badRequest()
                .body(Map.of("message", "Validation failed", "details", details));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntime(RuntimeException ex) {
        return ResponseEntity.internalServerError().body(Map.of("message", "Internal server error"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "غير مصرح لك بالوصول"));
    }

    // @ExceptionHandler(Exception.class)
    // public String handleGeneral(Exception ex, Model model) {
    // model.addAttribute("error", ex.getMessage());
    // model.addAttribute("status", 500);
    // return "error";
    // }

    @ExceptionHandler(NotValidTimeException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(NotValidTimeException ex) {
        return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
    }

}