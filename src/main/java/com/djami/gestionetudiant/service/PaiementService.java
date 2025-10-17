package com.djami.gestionetudiant.service;

import com.djami.gestionetudiant.model.Paiement;
import java.time.LocalDateTime;
import java.util.List;

public interface PaiementService {

    Paiement creerPaiementAvecEtudiantId(Double montant, String motif, Long etudiantId,
                                         String numeroRecu, LocalDateTime datePaiement);

    Paiement creerPaiement(Paiement paiement, Long etudiantId);

    // ✅ CORRECTION : Ajouter la méthode findAll() manquante
    List<Paiement> findAll();

    List<Paiement> getPaiementsParEtudiant(Long etudiantId);
    Paiement findByNumeroRecu(String numeroRecu);
    Double calculerTotalPaiements(Long etudiantId);
    boolean reçuExisteDeja(String numeroRecu);
}