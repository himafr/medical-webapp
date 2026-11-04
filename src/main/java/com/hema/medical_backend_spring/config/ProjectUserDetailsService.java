package com.hema.medical_backend_spring.config;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hema.medical_backend_spring.model.ProjectUser;
import com.hema.medical_backend_spring.repository.UserRepo;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProjectUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ProjectUser user = userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("لم يتم العثور على حساب بهذا البريد الإلكتروني"));
        List<GrantedAuthority> authorities = List.of(
            new SimpleGrantedAuthority(user.getRole().toString())
        );
        return new CustomUserDetails(user.getEmail(),user.getPassword(),user,user.isActive(),authorities);
    }

}
