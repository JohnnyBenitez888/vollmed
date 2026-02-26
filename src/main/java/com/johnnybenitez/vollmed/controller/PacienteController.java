package com.johnnybenitez.vollmed.controller;

import com.johnnybenitez.vollmed.medico.DatosRegistroMedico;
import com.johnnybenitez.vollmed.medico.Medico;
import com.johnnybenitez.vollmed.medico.MedicoRepository;
import com.johnnybenitez.vollmed.paciente.DatosRegistroPaciente;
import com.johnnybenitez.vollmed.paciente.Paciente;
import com.johnnybenitez.vollmed.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepo;

    @Transactional /*Agregar o modificar en la base de datos*/
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroPaciente datos) {
        // Lógica para ingresar un nuevo médico
        pacienteRepo.save(new Paciente(datos));
    }
}
