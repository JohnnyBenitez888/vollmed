package com.johnnybenitez.vollmed.controller;

import com.johnnybenitez.vollmed.domain.paciente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepo;

    @Transactional /*Agregar o modificar en la base de datos*/
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroPaciente datos, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(datos);
        pacienteRepo.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetallePaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaPaciente>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {

        var page = pacienteRepo.findAllByActivoTrue(paginacion).map(DatosListaPaciente::new);
        return ResponseEntity.ok(page);
        /*return medicoRepo.findAll().stream()
                .map(x -> new DatosListaMedico(x.getNombre(), x.getEmail(), x.getDocumento(), x.getEspecialidad()))
                .toList();*/
    }

    @Transactional /*Agregar o modificar en la base de datos*/
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionPaciente datos) {
        var paciente = pacienteRepo.getReferenceById(datos.id());
        paciente.actualizarInformaciones(datos);

        return ResponseEntity.ok(new DatosDetallePaciente(paciente));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {/*Obtenemos el ID de la url*/
        var paciente = pacienteRepo.getReferenceById(id);
        paciente.eliminar();
        return ResponseEntity.noContent().build();
    }

    /*Sin Transactional porque vamos a traer algo*/
    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {/*Obtenemos el ID de la url*/
        var paciente = pacienteRepo.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetallePaciente(paciente));
    }
}

