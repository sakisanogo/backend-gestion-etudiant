package com.djami.gestionetudiant.service.impl;

import com.djami.gestionetudiant.model.Role;
import com.djami.gestionetudiant.model.User;
import com.djami.gestionetudiant.repository.RoleRepository;
import com.djami.gestionetudiant.repository.UserRepository;
import com.djami.gestionetudiant.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(String username, String password, String roleName) {
        // Validation
        if (username == null || username.trim().isEmpty()) {
            throw new RuntimeException("Le nom d'utilisateur est requis");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new RuntimeException("Le mot de passe est requis");
        }
        if (password.length() < 6) {
            throw new RuntimeException("Le mot de passe doit contenir au moins 6 caractères");
        }

        // Vérifier si l'utilisateur existe déjà
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Ce nom d'utilisateur est déjà utilisé");
        }

        // Trouver ou créer le rôle
        Role role = roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    // Créer le rôle s'il n'existe pas
                    Role newRole = new Role(roleName);
                    return roleRepository.save(newRole);
                });

        // Créer l'utilisateur
        User user = new User();
        user.setUsername(username.trim());
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Collections.singletonList(role));
        user.setEnabled(true);

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, String username, String password, List<String> roleNames) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        user.setUsername(username);
        if (password != null && !password.isEmpty()) {
            user.setPassword(passwordEncoder.encode(password));
        }

        List<Role> roles = roleNames.stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Rôle non trouvé: " + roleName)))
                .toList();

        user.setRoles(roles);
        return userRepository.save(user);
    }
}