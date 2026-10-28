package com.hema.medical_backend_spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hema.medical_backend_spring.model.ProjectUser;
import com.hema.medical_backend_spring.model.ProjectUser.Role;
import com.hema.medical_backend_spring.repository.UserRepo;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WelcomController {

    private final UserRepo userRepo;

    @GetMapping("/welcome")
    public String greeting() {
        return new String("welcome to our new project.");
    }

    @PostMapping("/register")
    public ProjectUser postMethodName(@RequestBody ProjectUser entity) throws Exception {
        entity.setRole(Role.PATIENT);
        ProjectUser user = userRepo.save(entity);
        ProjectUser nUser = userRepo.findByEmail(user.getEmail()).orElseThrow(
                () -> new Exception("could not find user"));
        return nUser;
    }

}
