package com.johnnybenitez.vollmed.controller;

import com.johnnybenitez.vollmed.medico.DatosRegistroMedico;
import com.johnnybenitez.vollmed.medico.Medico;
import com.johnnybenitez.vollmed.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepo;

    @PostMapping
    public void registrar(@RequestBody DatosRegistroMedico datos) {
        // Lógica para ingresar un nuevo médico
            medicoRepo.save(new Medico(datos));
    }
}
