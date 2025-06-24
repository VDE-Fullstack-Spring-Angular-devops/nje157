package com.vde.gestionetudiants.service;

import com.vde.gestionetudiants.modele.Cours;
import com.vde.gestionetudiants.repository.CourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourService {

    @Autowired
    private CourRepository courRepository;


    public List<Cours> getAllCour(){
        return courRepository.findAll();
    }

    public Cours getCour(Long id) {
        return courRepository.findById(id).get();
    }

    public Cours saveCour(Cours cour) {
       return courRepository.save(cour);
    }

    public  void deleteCour(Long id) {
        courRepository.deleteById(id);
    }

}
