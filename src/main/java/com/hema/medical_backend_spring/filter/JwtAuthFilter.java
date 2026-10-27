package com.hema.medical_backend_spring.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hema.medical_backend_spring.config.CustomUserDetails;
import com.hema.medical_backend_spring.model.ProjectUser;
import com.hema.medical_backend_spring.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String token = extractToken(request);

        if (token == null) {
            HttpSession session = request.getSession(false);
            if (session != null)
                session.invalidate();
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String email = jwtService.extractEmail(token);
            String role = jwtService.extractRole(token);
            Long userId = jwtService.extractUserId(token);
            boolean isActive = jwtService.extractIsActive(token);

            if (!jwtService.isTokenExpired(token)) {

                if (!isActive) {
                    String requestURI = request.getRequestURI();
                    String method = request.getMethod();

                    if (method.equals("POST") &&
                            !requestURI.startsWith("/activate") &&
                            !requestURI.startsWith("/login") &&
                            !requestURI.startsWith("/register")) {
                        if (requestURI.startsWith("/api/")) {
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter()
                                    .write("{\"message\": \"Account is not activated\", \"email\": \"" + email + "\"}");
                            return;
                        } else {
                            response.sendRedirect("/activate?email=" + email);
                            return;
                        }
                    }

                }

            }

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                ProjectUser user = new ProjectUser();
                user.setId(userId);
                user.setEmail(email);
                user.setRole(ProjectUser.Role.valueOf(role));
                user.setActive(isActive);

                CustomUserDetails userDetails = new CustomUserDetails(
                        email, "", user, isActive,
                        List.of(new SimpleGrantedAuthority(role.toString())));

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);

                HttpSession session = request.getSession(true);
                session.setAttribute(
                        HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                        context);
                if (!request.getRequestURI().startsWith("/api/")) {
                    HttpSession session1 = request.getSession(true);
                    session1.setAttribute(
                            HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                            context);
                }

            }

        } catch (Exception e) {
            System.out.println(e);
            SecurityContextHolder.clearContext();
            if (request.getRequestURI().startsWith("/api/")) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"message\": \"Invalid or expired token\"}");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("jwt")) {
                return cookie.getValue();
            }
        }
        return null;
    }
}