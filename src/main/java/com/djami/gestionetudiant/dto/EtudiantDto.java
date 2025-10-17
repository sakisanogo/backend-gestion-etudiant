package com.djami.gestionetudiant.dto;


import java.util.ArrayList;
import java.util.List;

public class EtudiantDto {
    private Long id;
    private String nom;
    private String prenom;
    private String matricule;
    private List<PaiementDto> paiements = new ArrayList<>();

    // Constructeurs
    public EtudiantDto() {}

    public EtudiantDto(Long id, String nom, String prenom, String matricule) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }

    public List<PaiementDto> getPaiements() { return paiements; }
    public void setPaiements(List<PaiementDto> paiements) { this.paiements = paiements; }
}
