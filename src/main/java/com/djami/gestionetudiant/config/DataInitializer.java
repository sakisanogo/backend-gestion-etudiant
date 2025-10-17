package com.djami.gestionetudiant.config;

import com.djami.gestionetudiant.model.Role;
import com.djami.gestionetudiant.model.User;
import com.djami.gestionetudiant.repository.RoleRepository;
import com.djami.gestionetudiant.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Créer les rôles s'ils n'existent pas
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));
            System.out.println("✅ Rôles créés: ADMIN, USER");
        }

        // Créer l'admin par défaut
        if (userRepository.findByUsername("admin").isEmpty()) {
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new RuntimeException("Rôle ADMIN non trouvé"));

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Collections.singletonList(adminRole));
            userRepository.save(admin);
            System.out.println("✅ Admin créé: admin / admin123");
        }

        // Créer un utilisateur par défaut
        if (userRepository.findByUsername("user").isEmpty()) {
            Role userRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Rôle USER non trouvé"));

            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRoles(Collections.singletonList(userRole));
            userRepository.save(user);
            System.out.println("✅ Utilisateur créé: user / user123");
        }
    }
}