package com.vde.gestionetudiants.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "etudiants")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_cour;
    @Column(name = "courname")
    String courname;
    @Column(name = "enseignant")
    String enseignant;


    @ManyToMany(mappedBy = "cours")
    @JsonIgnore
    Set<Etudiant> etudiants = new HashSet<>();

    public void addEtudiant(Etudiant etudiant) {
        this.etudiants.add(etudiant);
        etudiant.getCours().add(this);
    }

}
