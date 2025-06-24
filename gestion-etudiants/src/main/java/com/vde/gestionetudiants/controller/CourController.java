package com.vde.gestionetudiants.controller;


import com.vde.gestionetudiants.modele.Cours;
import com.vde.gestionetudiants.service.CourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cours")
public class CourController {

    @Autowired
    private CourService courService;

    @GetMapping("")
    public List<Cours> getAllCours(){
        return courService.getAllCour();
    }

    @GetMapping("/{id}")
    public Cours getCourById(@PathVariable("id") long id) {
        return courService.getCour(id);
    }

    @PostMapping(value = "/create")
    public Cours createCour(@RequestBody Cours cour) {
       return courService.saveCour(cour);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourById(@PathVariable("id") long id) {
        courService.deleteCour(id);
        return "Cour deleted :" + id;
    }
}
