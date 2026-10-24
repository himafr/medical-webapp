package com.hema.medical_backend_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import com.hema.medical_backend_spring.filter.JwtAuthFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ProjectSecurityConfig {

        private final JwtAuthFilter jwtAuthFilter;
        private final CustomAuthSuccessHandler customAuthSuccessHandler;

        @Bean
        SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
                http

                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .loginProcessingUrl("/perform_login")
                                                .successHandler(customAuthSuccessHandler)
                                                .failureUrl("/login?error=true")
                                                .permitAll())

                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                                .securityContext(context -> context
                                                .securityContextRepository(securityContextRepository()))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/customLogin", "/signup", "/css/**", "/login",
                                                                "api/auth/login", "api/auth/register",
                                                                "/api/auth/activate/**", "/api/auth/activate/send",
                                                                "/api/auth/activate/verify",
                                                                "/js/**",
                                                                "/images/**", "/fonts/**", "/", "/contact", "/about",
                                                                "/svgs/**", "/style.css", "/responsive.css",
                                                                "/WEB-INF/views/**", "/home", "/error",
                                                                "/activate", "/activate/send", "/activate/verify")
                                                .permitAll()
                                                .requestMatchers("/admin/**", "/doctor-signup").hasAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.POST, "/services/**").hasAuthority("ADMIN")
                                                .requestMatchers(HttpMethod.POST, "/doctors/**").hasAuthority("DOCTOR")
                                                .requestMatchers(HttpMethod.POST, "/patient/**").hasAuthority("PATIENT")
                                                .anyRequest().authenticated())
                                .exceptionHandling(ex -> ex
                                                .authenticationEntryPoint(customEntryPoint()))
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login?logout=true")
                                                .deleteCookies("jwt"))
                                .csrf(csrf -> csrf.disable());
                return http.build();
        }

        // @Bean
        // UserDetailsService userDetailsService(){
        // UserDetails
        // user1=User.withUsername("hema").password("{noop}12345").authorities("read").build();
        // UserDetails
        // user2=User.withUsername("admin").password("{noop}12345").authorities("admin").build();

        // return new InMemoryUserDetailsManager(user1,user2);
        // }
        @Bean
        SecurityContextRepository securityContextRepository() {
                return new DelegatingSecurityContextRepository(
                                new HttpSessionSecurityContextRepository(),
                                new RequestAttributeSecurityContextRepository());
        }

        @Bean
        PasswordEncoder passwordEncoder() {
                return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }

       @Bean
AuthenticationEntryPoint customEntryPoint() {
    return (request, response, authException) -> {
        String uri = request.getRequestURI();

        // 1. If the request is for an API endpoint, return JSON
        if (uri.startsWith("/api/")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"message\": \"Forbidden: You do not have permission\"}");
            return;
        }

        // 2. Existing redirect logic for web pages
        if (uri.equals("/login") || uri.equals("/perform_login")) {
            response.sendRedirect("/login");
            return;
        }

        response.sendRedirect("/login?redirect=" + uri);
    };
}
}
