package com.vde.gestionetudiants.modele;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name= "etudiants")
public class Etudiant {
    @Id
    @Column(name = "idetudiant")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @JsonProperty("firstname")
    @Column(name = "firstname")
    String firstName;

    @JsonProperty("lastname")
    @Column(name = "lastname")
    String lastName;

    String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "etudiants_cours",
            joinColumns = @JoinColumn(name = "idetudiant"),
            inverseJoinColumns = @JoinColumn(name = "id_cour"))
    Set<Cours> cours = new HashSet<>();

    public Etudiant(long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void addCours(Cours cours) {
        this.cours.add(cours);
        cours.getEtudiants().add(this); // <-- essentiel pour que Hibernate voie la relation complète
    }

}
