package com.djami.gestionetudiant.model;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    // Constructeurs
    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Collection<User> getUsers() { return users; }
    public void setUsers(Collection<User> users) { this.users = users; }
}