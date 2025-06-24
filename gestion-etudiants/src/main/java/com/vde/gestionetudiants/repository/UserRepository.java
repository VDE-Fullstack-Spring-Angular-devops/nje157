package com.vde.gestionetudiants.repository;

import com.vde.gestionetudiants.modele.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
   Optional<UserEntity> findByLoginIgnoreCase(String login);
}

