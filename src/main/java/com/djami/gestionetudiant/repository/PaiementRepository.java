package com.djami.gestionetudiant.repository;

import com.djami.gestionetudiant.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {

    // ✅ Méthodes avec @Query explicites
    @Query("SELECT p FROM Paiement p WHERE p.etudiant.id = :etudiantId")
    List<Paiement> trouverParEtudiantId(@Param("etudiantId") Long etudiantId);

    @Query("SELECT p FROM Paiement p WHERE p.numeroRecu = :numeroRecu")
    Paiement trouverParNumeroRecu(@Param("numeroRecu") String numeroRecu);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Paiement p WHERE p.numeroRecu = :numeroRecu")
    boolean existeParNumeroRecu(@Param("numeroRecu") String numeroRecu);

    @Query("SELECT SUM(p.montant) FROM Paiement p WHERE p.etudiant.id = :etudiantId")
    Double calculerTotalPaiementsParEtudiantId(@Param("etudiantId") Long etudiantId);

    // ✅ findAll() est déjà fourni par JpaRepository, pas besoin de le redéfinir
}