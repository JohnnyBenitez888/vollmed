package com.johnnybenitez.vollmed.controller;

import com.johnnybenitez.vollmed.domain.consulta.DatosCancelamientoConsulta;
import com.johnnybenitez.vollmed.domain.consulta.DatosDetalleConsulta;
import com.johnnybenitez.vollmed.domain.consulta.DatosReservaConsulta;
import com.johnnybenitez.vollmed.domain.consulta.ReservaDeConsultas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private ReservaDeConsultas reserva;

    @Transactional
    @PostMapping
    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsulta datos) {
        System.out.println(datos);
        reserva.reservar(datos);
        return ResponseEntity.ok(new DatosDetalleConsulta(null, null, null, null));

    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelamientoConsulta datos) {
        reserva.cancelar(datos);
        return ResponseEntity.noContent().build();
    }
}
