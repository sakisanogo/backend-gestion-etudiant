package com.djami.gestionetudiant.controller;

import com.djami.gestionetudiant.model.Etudiant;
import com.djami.gestionetudiant.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    // VOS METHODES EXISTANTES (NE PAS MODIFIER)
    @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        Optional<Etudiant> etudiant = etudiantService.findById(id);
        return etudiant.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Etudiant createEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.save(etudiant);
    }

    @GetMapping("/{id}/avec-paiements")
    public ResponseEntity<Etudiant> getEtudiantAvecPaiements(@PathVariable Long id) {
        Optional<Etudiant> etudiant = etudiantService.findByIdWithPaiements(id);
        return etudiant.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/matricule/{matricule}")
    public ResponseEntity<Etudiant> getEtudiantByMatricule(@PathVariable String matricule) {
        Optional<Etudiant> etudiant = etudiantService.findByMatricule(matricule);
        return etudiant.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔥 REMPLACER UNIQUEMENT LA METHODE DELETE dans EtudiantController
   // 🔥 MODIFIER UNIQUEMENT CETTE METHODE POUR LA SUPPRESSION COMPLETE
        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteEtudiant(@PathVariable Long id) {
            try {
                Optional<Etudiant> etudiantOpt = etudiantService.findByIdWithPaiements(id);

                if (etudiantOpt.isPresent()) {
                    Etudiant etudiant = etudiantOpt.get();

                    // Log pour le débogage
                    if (etudiant.getPaiements() != null && !etudiant.getPaiements().isEmpty()) {
                        System.out.println("🗑️  Suppression de l'étudiant " + id + " avec " +
                                etudiant.getPaiements().size() + " paiement(s) associé(s)");
                    } else {
                        System.out.println("🗑️  Suppression de l'étudiant " + id + " (aucun paiement associé)");
                    }

                    // La cascade va automatiquement supprimer les paiements
                    etudiantService.deleteById(id);

                    System.out.println("✅ Étudiant ET paiements supprimés avec succès, ID: " + id);
                    return ResponseEntity.ok().build();

                } else {
                    System.out.println("❌ Étudiant non trouvé, ID: " + id);
                    return ResponseEntity.notFound().build();
                }
            } catch (Exception e) {
                System.err.println("❌ Erreur lors de la suppression de l'étudiant ID: " + id);
                System.err.println("Message d'erreur: " + e.getMessage());
                return ResponseEntity.internalServerError().body("Erreur lors de la suppression: " + e.getMessage());
            }
        }
    }