package com.djami.gestionetudiant.service.impl;

import com.djami.gestionetudiant.model.Etudiant;
import com.djami.gestionetudiant.model.Paiement;
import com.djami.gestionetudiant.repository.EtudiantRepository;
import com.djami.gestionetudiant.repository.PaiementRepository;
import com.djami.gestionetudiant.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PaiementServiceImpl implements PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public Paiement creerPaiementAvecEtudiantId(Double montant, String motif, Long etudiantId,
                                                String numeroRecu, LocalDateTime datePaiement) {
        try {
            System.out.println("💾 Création paiement avec données Angular:");
            System.out.println("  - Montant: " + montant);
            System.out.println("  - Motif: " + motif);
            System.out.println("  - EtudiantId: " + etudiantId);
            System.out.println("  - NumeroRecu: " + numeroRecu);
            System.out.println("  - DatePaiement: " + datePaiement);

            // Vérifier si l'étudiant existe
            Optional<Etudiant> etudiantOpt = etudiantRepository.findById(etudiantId);
            if (etudiantOpt.isEmpty()) {
                throw new RuntimeException("Étudiant non trouvé avec l'ID: " + etudiantId);
            }
            Etudiant etudiant = etudiantOpt.get();

            // Créer l'objet Paiement
            Paiement paiement = new Paiement();
            paiement.setMontant(montant);
            paiement.setMotif(motif);
            paiement.setEtudiant(etudiant);
            paiement.setNumeroRecu(numeroRecu);
            paiement.setDatePaiement(datePaiement);

            // Sauvegarder
            Paiement savedPaiement = paiementRepository.save(paiement);
            System.out.println("✅ Paiement créé avec ID: " + savedPaiement.getId());

            return savedPaiement;

        } catch (Exception e) {
            System.out.println("❌ Erreur création paiement: " + e.getMessage());
            throw new RuntimeException("Erreur création paiement: " + e.getMessage());
        }
    }

    @Override
    public Paiement creerPaiement(Paiement paiement, Long etudiantId) {
        // Vérifier si l'étudiant existe
        Optional<Etudiant> etudiantOpt = etudiantRepository.findById(etudiantId);
        if (etudiantOpt.isEmpty()) {
            throw new RuntimeException("Étudiant non trouvé avec l'ID: " + etudiantId);
        }
        Etudiant etudiant = etudiantOpt.get();

        // Générer un numéro de reçu unique si non fourni
        if (paiement.getNumeroRecu() == null || paiement.getNumeroRecu().isEmpty()) {
            String numeroRecu;
            do {
                numeroRecu = genererNumeroRecu();
            } while (reçuExisteDeja(numeroRecu));
            paiement.setNumeroRecu(numeroRecu);
        }

        // Définir la date si non fournie
        if (paiement.getDatePaiement() == null) {
            paiement.setDatePaiement(LocalDateTime.now());
        }

        paiement.setEtudiant(etudiant);

        return paiementRepository.save(paiement);
    }

    // ✅ CORRECTION : Implémenter findAll()
    @Override
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }

    @Override
    public List<Paiement> getPaiementsParEtudiant(Long etudiantId) {
        return paiementRepository.trouverParEtudiantId(etudiantId);
    }

    @Override
    public Paiement findByNumeroRecu(String numeroRecu) {
        return paiementRepository.trouverParNumeroRecu(numeroRecu);
    }

    @Override
    public Double calculerTotalPaiements(Long etudiantId) {
        Double total = paiementRepository.calculerTotalPaiementsParEtudiantId(etudiantId);
        return total != null ? total : 0.0;
    }

    @Override
    public boolean reçuExisteDeja(String numeroRecu) {
        return paiementRepository.existeParNumeroRecu(numeroRecu);
    }

    private String genererNumeroRecu() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String timestamp = now.format(formatter);
        return "RECU-" + timestamp;
    }
}