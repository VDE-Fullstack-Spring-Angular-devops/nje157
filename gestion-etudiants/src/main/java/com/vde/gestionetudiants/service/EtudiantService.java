package com.vde.gestionetudiants.service;

import com.vde.gestionetudiants.modele.Cours;
import com.vde.gestionetudiants.modele.Etudiant;
import com.vde.gestionetudiants.repository.CourRepository;
import com.vde.gestionetudiants.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private CourRepository coursRepository;

    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public List<Etudiant> getEtudiants() {
        return etudiantRepository.findAll();
    }

    public Etudiant getEtudiantById(Long id) {
        return etudiantRepository.findById(id).get();
    }

    public Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public void deleteEtudiantById(Long id) {
        etudiantRepository.deleteById(id);
    }

    public Etudiant updateEtudiant(Long id, Etudiant etudiant) {
        Optional<Etudiant> etu = etudiantRepository.findById(id);
        if (etu.isPresent()) {
            Etudiant currentEtudiant = etu.get();
            String firstName = etudiant.getFirstName();
            if (firstName != null) {
                currentEtudiant.setFirstName(firstName);
            }
            String lastName = etudiant.getLastName();
            if (firstName != null) {
                currentEtudiant.setLastName(lastName);
            }
            String email = etudiant.getEmail();
            if (email != null) {
                currentEtudiant.setEmail(email);
            }
            etudiantRepository.save(currentEtudiant);
            return currentEtudiant;
        } else {
            return null;
        }
    }

    public Etudiant addCoursToEtudiant(Long etudiantId,Long coursId ) {
        Etudiant etudiant = etudiantRepository.findById(etudiantId).get();
        Cours course = coursRepository.findById(coursId).get();
        etudiant.getCours().add(course);
        return saveEtudiant(etudiant);
    }

  /*  public Etudiant removeCours(Integer etudiantId, Integer coursId) {
        Etudiant etudiant = getEtudiantById(etudiantId);
        if(etudiant!=null){
            Cours cours =etudiant.getCours().stream().filter(c -> c.getId_cour() == coursId).findFirst().get();
            if (cours != null) {
                etudiant.removeCours(cours);
                return saveEtudiant(etudiant);
            }
        }
        return null;
    }*/
}
