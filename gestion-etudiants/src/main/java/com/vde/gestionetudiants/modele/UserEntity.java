package com.vde.gestionetudiants.modele;


import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name= "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    private String firstname;
    private String lastname;
    private String login;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns =@JoinColumn(name ="user_id" ),
            inverseJoinColumns =@JoinColumn(name ="role_name"))
            private Set<Roles> roles = new HashSet<>();
}
