package com.vde.gestionetudiants.controller;

import com.vde.gestionetudiants.modele.LoginRequest;
import com.vde.gestionetudiants.security.services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.AuthenticationException;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class LoginController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    @CrossOrigin( origins = "http://localhost:42000")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getLogin(),
                            loginRequest.getPassword()
                    )
            );

            String token = jwtService.generateToken(authentication);

            // Retourne un JSON avec le token
            return ResponseEntity.ok(Collections.singletonMap("token",token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants incorrects");
        }
    }


    @GetMapping("/check-roles")
    public ResponseEntity<?> checkUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("Utilisateur : " + authentication.getName());
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            System.out.println("Rôle : " + authority.getAuthority());
        }
        return ResponseEntity.ok("Check dans les logs");
    }
}
