package com.djami.gestionetudiant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "paiements")
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double montant;

    @Column(nullable = false)
    private String motif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etudiant_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "paiements"})
    private Etudiant etudiant;

    @Column(name = "numero_recu", nullable = false, unique = true)
    private String numeroRecu;

    @Column(name = "date_paiement", nullable = false)
    private LocalDateTime datePaiement;

    // CONSTRUCTEURS
    public Paiement() {
    }

    public Paiement(Double montant, String motif, Etudiant etudiant, String numeroRecu, LocalDateTime datePaiement) {
        this.montant = montant;
        this.motif = motif;
        this.etudiant = etudiant;
        this.numeroRecu = numeroRecu;
        this.datePaiement = datePaiement;
    }

    // GETTERS
    public Long getId() {
        return this.id;
    }

    public Double getMontant() {
        return this.montant;
    }

    public String getMotif() {
        return this.motif;
    }

    public Etudiant getEtudiant() {
        return this.etudiant;
    }

    public String getNumeroRecu() {
        return this.numeroRecu;
    }

    public LocalDateTime getDatePaiement() {
        return this.datePaiement;
    }

    // SETTERS
    public void setId(Long id) {
        this.id = id;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public void setNumeroRecu(String numeroRecu) {
        this.numeroRecu = numeroRecu;
    }

    public void setDatePaiement(LocalDateTime datePaiement) {
        this.datePaiement = datePaiement;
    }

    // ✅ CORRECTION : Méthode sécurisée pour obtenir l'ID de l'étudiant
    @JsonIgnore
    public Long getEtudiantId() {
        if (this.etudiant != null) {
            // ✅ Vérification que la méthode getId() existe
            return this.etudiant.getId();
        }
        return null;
    }

    @Override
    public String toString() {
        Long etudiantId = null;
        if (this.etudiant != null) {
            etudiantId = this.etudiant.getId(); // ✅ Utilisation sécurisée
        }

        return "Paiement{" +
                "id=" + id +
                ", montant=" + montant +
                ", motif='" + motif + '\'' +
                ", etudiantId=" + etudiantId +
                ", numeroRecu='" + numeroRecu + '\'' +
                ", datePaiement=" + datePaiement +
                '}';
    }
}