package com.djami.gestionetudiant.service.impl;


import com.djami.gestionetudiant.model.Etudiant;
import com.djami.gestionetudiant.repository.EtudiantRepository;
import com.djami.gestionetudiant.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EtudiantServiceImpl implements EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }



    @Override
    public Optional<Etudiant> findById(Long id) {
        return etudiantRepository.findById(id);
    }

    @Override
    public Etudiant save(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public void deleteById(Long id) {
        etudiantRepository.deleteById(id);
    }

    @Override
    public Optional<Etudiant> findByIdWithPaiements(Long id) {
        return etudiantRepository.findByIdWithPaiements(id);
    }

    @Override
    public Optional<Etudiant> findByMatricule(String matricule) {
        return etudiantRepository.findByMatricule(matricule);
    }

    @Override
    public boolean existsByMatricule(String matricule) {
        return etudiantRepository.existsByMatricule(matricule);
    }



}





