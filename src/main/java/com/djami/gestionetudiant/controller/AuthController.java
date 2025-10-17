package com.djami.gestionetudiant.controller;

import com.djami.gestionetudiant.model.User;
import com.djami.gestionetudiant.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        try {
            User user = userService.createUser(request.username(), request.password(), "USER");
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public record RegisterRequest(String username, String password) {}
}