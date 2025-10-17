package com.djami.gestionetudiant.service.impl;

import com.djami.gestionetudiant.model.Role;
import com.djami.gestionetudiant.repository.RoleRepository;
import com.djami.gestionetudiant.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(String roleName) {
        if (roleRepository.findByName(roleName).isPresent()) {
            throw new RuntimeException("Le rôle existe déjà: " + roleName);
        }

        Role role = new Role(roleName);
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}