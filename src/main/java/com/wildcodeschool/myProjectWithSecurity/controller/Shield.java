package com.wildcodeschool.myProjectWithSecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class Shield {

    // création des 3 routes demandées
    @GetMapping (value = "/")
    public String hello(){
        return "Welcome to the SHIELD !";
    }

    @GetMapping (value = "/avengers/assemble")
    @PreAuthorize("hasRole('CHAMPION')")
    public String assemble(){
        return "Avengers... Assemble";
    }

    @GetMapping(value = "/secret-bases")
    @PreAuthorize("hasRole('DIRECTOR')")
    public List<String> secret(){
        List<String> campus = Arrays.asList("Bordeau", "Lyon", "Nantes", "Lille", "Paris", "Toulouse");
        return campus;
    }

}
