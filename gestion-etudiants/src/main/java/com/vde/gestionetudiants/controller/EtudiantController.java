package com.vde.gestionetudiants.controller;
import com.vde.gestionetudiants.modele.Etudiant;
import com.vde.gestionetudiants.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;




    @GetMapping("")
    public List<Etudiant> getEtudiants() {
            return etudiantService.getEtudiants();
    }

    @GetMapping("/{id}")
    public Etudiant getEtudiantById(@PathVariable("id") long id){
        return etudiantService.getEtudiantById(id);
    }

    @PostMapping("/create")
    public Etudiant addEtudiant(@RequestBody Etudiant etudiant) {
         return etudiantService.saveEtudiant(etudiant);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteEtudiant(@PathVariable("id") long id) {
        etudiantService.deleteEtudiantById(id);
    }

    @PutMapping("/update/{id}")
    public Etudiant updateEtudiant(@PathVariable("id") long id,@RequestBody Etudiant etudiant) {
        return etudiantService.updateEtudiant(id, etudiant);
    }

    @PutMapping("/{etudiantId}/cours/{coursId}")
    public  Etudiant addcours(@PathVariable(name = "etudiantId") long etudiantId, @PathVariable(name = "coursId") long coursId) {
       return etudiantService.addCoursToEtudiant(etudiantId, coursId);
    }
}
