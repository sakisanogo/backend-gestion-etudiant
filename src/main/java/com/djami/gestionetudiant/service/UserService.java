package com.djami.gestionetudiant.service;

import com.djami.gestionetudiant.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(String username, String password, String roleName);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    void deleteUser(Long id);
    User updateUser(Long id, String username, String password, List<String> roleNames);
}