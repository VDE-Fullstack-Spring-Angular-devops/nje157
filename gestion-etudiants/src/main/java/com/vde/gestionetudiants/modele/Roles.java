package com.vde.gestionetudiants.modele;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Roles {
    @Id
    @Column(name = "role_name")
    private String roleName;
}
