package com.johnnybenitez.vollmed.controller;

import com.johnnybenitez.vollmed.domain.medico.*;
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
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepo;

    @Transactional /*Agregar o modificar en la base de datos*/
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroMedico datos, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(datos);
        medicoRepo.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaMedico>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {

        var page = medicoRepo.findAllByActivoTrue(paginacion).map(DatosListaMedico::new);
        return ResponseEntity.ok(page);
        /*return medicoRepo.findAll().stream()
                .map(x -> new DatosListaMedico(x.getNombre(), x.getEmail(), x.getDocumento(), x.getEspecialidad()))
                .toList();*/
    }

    @Transactional /*Agregar o modificar en la base de datos*/
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionMedico datos) {
        var medico = medicoRepo.getReferenceById(datos.id());
        medico.actualizarInformaciones(datos);

        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {/*Obtenemos el ID de la url*/
        var medico = medicoRepo.getReferenceById(id);
        medico.eliminar();
        return ResponseEntity.noContent().build();
    }

    /*Sin Transactional porque vamos a traer algo*/
    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {/*Obtenemos el ID de la url*/
        var medico = medicoRepo.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }
}
