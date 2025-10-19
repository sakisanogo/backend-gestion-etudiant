package com.djami.gestionetudiant.controller;

import com.djami.gestionetudiant.model.User;
import com.djami.gestionetudiant.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    /*private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ ENDPOINT D'INSCRIPTION PUBLIC
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        try {
            System.out.println("📝 Tentative d'inscription: " + request.username());

            User user = userService.createUser(request.username(), request.password(), "USER");

            System.out.println("✅ Utilisateur inscrit avec succès: " + user.getUsername());

            // Retourner une réponse simplifiée sans le mot de passe
            return ResponseEntity.ok(new RegisterResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getRoles().stream().map(role -> role.getName()).toList()
            ));

        } catch (RuntimeException e) {
            System.out.println("❌ Erreur inscription: " + e.getMessage());
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    // ✅ ENDPOINT ADMIN - Création utilisateur
    /*@PostMapping("/admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUserAdmin(@RequestBody CreateUserRequest request) {
        try {
            User user = userService.createUser(request.username(), request.password(), request.role());
            return ResponseEntity.ok(new RegisterResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getRoles().stream().map(role -> role.getName()).toList()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }*/

    // ✅ ENDPOINT ADMIN - Liste utilisateurs
    /*@GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/admin/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/admin/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    // ✅ RECORDS POUR LES RÉPONSES
    public record RegisterRequest(String username, String password) {}
    public record CreateUserRequest(String username, String password, String role) {}
    public record RegisterResponse(Long id, String username, List<String> roles) {}
    public record ErrorResponse(String message) {}*/
}