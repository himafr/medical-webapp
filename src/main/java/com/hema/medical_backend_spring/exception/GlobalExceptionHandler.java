package com.hema.medical_backend_spring.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public Object handleNotFound(NoResourceFoundException ex, Model model,
            HttpServletRequest request, Authentication authentication) throws NoResourceFoundException {
        String path = request.getRequestURI();

        // let static resources 404 normally
        if (path.endsWith(".css") || path.endsWith(".js") ||
                path.endsWith(".png") || path.endsWith(".jpg") ||
                path.endsWith(".svg") || path.endsWith(".ico")) {
            throw ex;
        }
        if (request.getRequestURI().startsWith("/api/")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "الصفحة غير موجودة"));
        }
        model.addAttribute("error", "الصفحة غير موجودة");
        model.addAttribute("status", 404);
        return "notFound";
    }

    @ExceptionHandler(RuntimeException.class)
    public Object handleRuntime(RuntimeException ex, Model model, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/api/")) {

            return ResponseEntity.internalServerError().body(Map.of("message", ex.getMessage()));

        }
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", 500);
        return "error";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Object handleAccessDenied(AccessDeniedException ex, Model model, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/api/")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message",
                    ex.getMessage()));
        }
        model.addAttribute("error", "غير مصرح لك بالوصول");
        model.addAttribute("status", 403);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public Object handleGeneral(Exception ex, Model model, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/api/")) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", 500);
        return "error";
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public Object incorrectPassword(Exception ex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/api/")) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid email or password"));
        }
        redirectAttributes.addFlashAttribute("failMessage", ex.getMessage());
        return "redirect:/fail";
    }

    @ExceptionHandler(NotValidTimeException.class)
    public Object handleValidationErrors(NotValidTimeException ex, Model model, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/api/")) {
            return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
        }
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", 400);
        return "error";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public Object handleValidationErrors(UserNotFoundException ex, Model model, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/api/")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", ex.getMessage()));
        }
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", 400);
        return "error";
    }

}
