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
        initializeRoles();
        initializeAdminUser();
        initializeTestUser();
    }

    private void initializeRoles() {
        // ✅ CRÉER RÔLE ADMIN
        if (roleRepository.findByName("ADMIN").isEmpty()) {
            Role adminRole = new Role("ADMIN");
            roleRepository.save(adminRole);
            System.out.println("✅ Rôle ADMIN créé");
        }

        // ✅ CRÉER RÔLE USER
        if (roleRepository.findByName("USER").isEmpty()) {
            Role userRole = new Role("USER");
            roleRepository.save(userRole);
            System.out.println("✅ Rôle USER créé");
        }
    }

    private void initializeAdminUser() {
        // ✅ CRÉER ADMIN PAR DÉFAUT
        if (userRepository.findByUsername("admin").isEmpty()) {
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new RuntimeException("Rôle ADMIN non trouvé"));

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Collections.singletonList(adminRole));
            admin.setEnabled(true);

            userRepository.save(admin);
            System.out.println("✅ Admin créé: admin / admin123");
        } else {
            System.out.println("ℹ️ Admin existe déjà");
        }
    }

    private void initializeTestUser() {
        // ✅ CRÉER UTILISATEUR TEST
        if (userRepository.findByUsername("user").isEmpty()) {
            Role userRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Rôle USER non trouvé"));

            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRoles(Collections.singletonList(userRole));
            user.setEnabled(true);

            userRepository.save(user);
            System.out.println("✅ Utilisateur créé: user / user123");
        } else {
            System.out.println("ℹ️ Utilisateur existe déjà");
        }
    }
}