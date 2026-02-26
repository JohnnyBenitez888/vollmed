package com.johnnybenitez.vollmed.controller;

import com.johnnybenitez.vollmed.medico.DatosListaMedico;
import com.johnnybenitez.vollmed.medico.DatosRegistroMedico;
import com.johnnybenitez.vollmed.medico.Medico;
import com.johnnybenitez.vollmed.medico.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepo;

    @Transactional /*Agregar o modificar en la base de datos*/
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistroMedico datos) {
        // Lógica para ingresar un nuevo médico
        medicoRepo.save(new Medico(datos));
    }

    @GetMapping
    public Page<DatosListaMedico> listar(@PageableDefault(size=10, sort={"nombre"}) Pageable paginacion) {

        return medicoRepo.findAll(paginacion).map(DatosListaMedico::new);
        /*return medicoRepo.findAll().stream()
                .map(x -> new DatosListaMedico(x.getNombre(), x.getEmail(), x.getDocumento(), x.getEspecialidad()))
                .toList();*/
    }
}
