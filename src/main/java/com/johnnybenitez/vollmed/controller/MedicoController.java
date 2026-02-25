package com.johnnybenitez.vollmed.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping
    public void registrar(@RequestBody String json) {
        // Lógica para ingresar un nuevo médico
        System.out.println("Registrar médico: " + json);
    }
}
