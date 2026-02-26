package com.johnnybenitez.vollmed.controller;

import com.johnnybenitez.vollmed.medico.DatosActualizacionMedico;
import com.johnnybenitez.vollmed.paciente.*;
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
    public Page<DatosListaPaciente> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {

        return pacienteRepo.findAllByActivoTrue(paginacion).map(DatosListaPaciente::new);
        /*return medicoRepo.findAll().stream()
                .map(x -> new DatosListaMedico(x.getNombre(), x.getEmail(), x.getDocumento(), x.getEspecialidad()))
                .toList();*/
    }

    @Transactional /*Agregar o modificar en la base de datos*/
    @PutMapping
    public void actualizar(@RequestBody @Valid DatosActualizacionPaciente datos) {
        var paciente = pacienteRepo.getReferenceById(datos.id());
        paciente.actualizarInformaciones(datos);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {/*Obtenemos el ID de la url*/
        var paciente = pacienteRepo.getReferenceById(id);
        paciente.eliminar();
    }
}
