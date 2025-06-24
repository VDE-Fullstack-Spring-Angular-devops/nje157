package com.vde.gestionetudiants.repository;

import com.vde.gestionetudiants.modele.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourRepository extends JpaRepository<Cours, Long> {
}
