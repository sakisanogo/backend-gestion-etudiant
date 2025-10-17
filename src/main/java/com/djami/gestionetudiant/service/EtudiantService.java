package com.djami.gestionetudiant.service;


import com.djami.gestionetudiant.model.Etudiant;

import java.util.List;
import java.util.Optional;

public interface EtudiantService {
    List<Etudiant> findAll();
    Optional<Etudiant> findById(Long id);
    Etudiant save(Etudiant etudiant);
    void deleteById(Long id);
    Optional<Etudiant> findByIdWithPaiements(Long id);
    Optional<Etudiant> findByMatricule(String matricule);
    boolean existsByMatricule(String matricule);
}