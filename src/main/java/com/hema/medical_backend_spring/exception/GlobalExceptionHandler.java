package com.hema.medical_backend_spring.exception;

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

import com.hema.medical_backend_spring.dto.ApiResponse;

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
                    .body(ApiResponse.error(404, "الصفحة غير موجودة"));
        }
        model.addAttribute("error", "الصفحة غير موجودة");
        model.addAttribute("status", 404);
        return "notFound";
    }

    @ExceptionHandler(RuntimeException.class)
    public Object handleRuntime(RuntimeException ex, Model model, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/api/")) {

            return ResponseEntity.internalServerError().body(ApiResponse.error(500, ex.getMessage()));

        }
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", 500);
        return "error";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Object handleAccessDenied(AccessDeniedException ex, Model model, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/api/")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponse.error(403, ex.getMessage()));
        }
        model.addAttribute("error", "غير مصرح لك بالوصول");
        model.addAttribute("status", 403);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public Object handleGeneral(Exception ex, Model model, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/api/")) {
            return ResponseEntity.badRequest().body(ApiResponse.error(400, ex.getMessage()));
        }
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", 400);
        return "error";
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public Object incorrectPassword(Exception ex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/api/")) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error(401, ex.getMessage()));
        }
        redirectAttributes.addFlashAttribute("failMessage", ex.getMessage());
        return "redirect:/fail";
    }

    @ExceptionHandler(NotValidTimeException.class)
    public Object handleValidationErrors(NotValidTimeException ex, Model model, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/api/")) {
            return ResponseEntity.badRequest().body(ApiResponse.error(400, ex.getMessage()));
        }
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", 400);
        return "error";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public Object handleValidationErrors(UserNotFoundException ex, Model model, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/api/")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(404, ex.getMessage()));
        }
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", 400);
        return "error";
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleValidation(MethodArgumentNotValidException ex, Model model, HttpServletRequest request) {
        if (request.getRequestURI().startsWith("/api/")) {
            String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .findFirst()
                .orElse("بيانات غير صحيحة");

        return ResponseEntity.badRequest().body(ApiResponse.error(400,message));
        }
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("status", 400);
        return "error";
       
    }


}
