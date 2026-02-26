package com.johnnybenitez.vollmed.controller;

import com.johnnybenitez.vollmed.paciente.DatosListaPaciente;
import com.johnnybenitez.vollmed.paciente.DatosRegistroPaciente;
import com.johnnybenitez.vollmed.paciente.Paciente;
import com.johnnybenitez.vollmed.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public Page<DatosListaPaciente> listar(@PageableDefault(size=10, sort={"nombre"}) Pageable paginacion) {

        return pacienteRepo.findAll(paginacion).map(DatosListaPaciente::new);
        /*return medicoRepo.findAll().stream()
                .map(x -> new DatosListaMedico(x.getNombre(), x.getEmail(), x.getDocumento(), x.getEspecialidad()))
                .toList();*/
    }
}
