package com.vde.gestionetudiants.service;

import com.vde.gestionetudiants.modele.Etudiant;
import com.vde.gestionetudiants.repository.EtudiantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class EtudiantServiceTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    private EtudiantService etudiantService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        etudiantService = new EtudiantService(etudiantRepository);
    }
    Etudiant etudiant_1 = new Etudiant(1,"John","Doe","doe@hotmail.fr");
    Etudiant etudiant_2 = new Etudiant(2,"Philipe","Lecompte","lecompte@hotmail.fr");
    Etudiant etudiant_3 = new Etudiant(3,"Mattias","lebon","lebon@hotmail.fr");

    List<Etudiant> etudiants = Arrays.asList(etudiant_1,etudiant_2,etudiant_3);


    @Test
    void shouldReturnListEtudiants() {
        Mockito.when(etudiantRepository.findAll()).thenReturn(etudiants);

        List<Etudiant> etudiants = etudiantService.getEtudiants();

        Assertions.assertNotNull(etudiants);
    }

    @Test
    void shouldReturnEtudiantById() {
        long etudiantId = 1;
        Mockito.when(etudiantRepository.findById(etudiantId)).thenReturn(Optional.of(etudiant_1));

        Etudiant findEtudiant = etudiantService.getEtudiantById(etudiantId);

        Assertions.assertNotNull(findEtudiant);
    }

    @Test
    void shouldSaveEtudiant() {
    }
}
