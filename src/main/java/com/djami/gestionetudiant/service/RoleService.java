package com.djami.gestionetudiant.service;

import com.djami.gestionetudiant.model.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role createRole(String roleName);
    List<Role> getAllRoles();
    Optional<Role> getRoleById(Long id);
    Optional<Role> getRoleByName(String name);
    void deleteRole(Long id);
}