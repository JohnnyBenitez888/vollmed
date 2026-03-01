package com.johnnybenitez.vollmed.controller;

import com.johnnybenitez.vollmed.consulta.DatosDetalleConsulta;
import com.johnnybenitez.vollmed.consulta.DatosReservaConsulta;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Transactional
    @PostMapping
    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsulta datos) {
        return ResponseEntity.ok(new DatosDetalleConsulta(null, null, null, null));

    }
}
