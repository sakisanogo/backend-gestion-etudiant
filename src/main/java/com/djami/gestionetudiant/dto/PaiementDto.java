package com.djami.gestionetudiant.dto;


import java.util.Date;

public class PaiementDto {
    private Long id;
    private Double montant;
    private Date datePaiement;
    private String numeroRecu;
    private String motif;
    private Long etudiantId;
    private String etudiantNom;
    private String etudiantPrenom;
    private String etudiantMatricule;

    // Constructeurs
    public PaiementDto() {}

    public PaiementDto(Long id, Double montant, Date datePaiement, String numeroRecu, String motif) {
        this.id = id;
        this.montant = montant;
        this.datePaiement = datePaiement;
        this.numeroRecu = numeroRecu;
        this.motif = motif;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }

    public Date getDatePaiement() { return datePaiement; }
    public void setDatePaiement(Date datePaiement) { this.datePaiement = datePaiement; }

    public String getNumeroRecu() { return numeroRecu; }
    public void setNumeroRecu(String numeroRecu) { this.numeroRecu = numeroRecu; }

    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }

    public Long getEtudiantId() { return etudiantId; }
    public void setEtudiantId(Long etudiantId) { this.etudiantId = etudiantId; }

    public String getEtudiantNom() { return etudiantNom; }
    public void setEtudiantNom(String etudiantNom) { this.etudiantNom = etudiantNom; }

    public String getEtudiantPrenom() { return etudiantPrenom; }
    public void setEtudiantPrenom(String etudiantPrenom) { this.etudiantPrenom = etudiantPrenom; }

    public String getEtudiantMatricule() { return etudiantMatricule; }
    public void setEtudiantMatricule(String etudiantMatricule) { this.etudiantMatricule = etudiantMatricule; }
}
