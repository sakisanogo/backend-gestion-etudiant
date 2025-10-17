package com.djami.gestionetudiant.controller;

import com.djami.gestionetudiant.model.Paiement;
import com.djami.gestionetudiant.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;

    // ✅ CORRECTION : Endpoint POST simplifié
    @PostMapping
    public ResponseEntity<Paiement> creerPaiementSimple(@RequestBody Paiement paiement) {
        try {
            System.out.println("📥 DONNÉES REÇUES: " + paiement);

            // Validation
            if (paiement.getEtudiant() == null || paiement.getEtudiant().getId() == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            // ✅ CORRECTION : Sauvegarde directe sans conflit d'ID
            Paiement nouveauPaiement = paiementService.creerPaiementSimple(paiement);
            return new ResponseEntity<>(nouveauPaiement, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            System.out.println("❌ ERREUR: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/etudiant/{etudiantId}")
    public ResponseEntity<Paiement> creerPaiement(@RequestBody Paiement paiement,
                                                  @PathVariable Long etudiantId) {
        try {
            Paiement nouveauPaiement = paiementService.creerPaiement(paiement, etudiantId);
            return new ResponseEntity<>(nouveauPaiement, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/etudiant/{etudiantId}")
    public List<Paiement> getPaiementsParEtudiant(@PathVariable Long etudiantId) {
        return paiementService.getPaiementsParEtudiant(etudiantId);
    }

    @GetMapping("/recherche")
    public ResponseEntity<Paiement> getPaiementParRecu(@RequestParam String numeroRecu) {
        Paiement paiement = paiementService.findByNumeroRecu(numeroRecu);
        if (paiement != null) {
            return ResponseEntity.ok(paiement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Paiement> getAllPaiements() {
        return paiementService.findAll();
    }

    @GetMapping("/etudiant/{etudiantId}/total")
    public Double getTotalPaiements(@PathVariable Long etudiantId) {
        return paiementService.calculerTotalPaiements(etudiantId);
    }

    @GetMapping("/verifier-recu")
    public boolean verifierRecuExiste(@RequestParam String numeroRecu) {
        return paiementService.reçuExisteDeja(numeroRecu);
    }
}