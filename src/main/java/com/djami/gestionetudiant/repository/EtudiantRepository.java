package com.djami.gestionetudiant.repository;

import com.djami.gestionetudiant.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    Optional<Etudiant> findByMatricule(String matricule);

    @Query("SELECT e FROM Etudiant e LEFT JOIN FETCH e.paiements WHERE e.id = :id")
    Optional<Etudiant> findByIdWithPaiements(@Param("id") Long id);

    boolean existsByMatricule(String matricule);
}