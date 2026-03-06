package com.johnnybenitez.vollmed.controller;

import com.johnnybenitez.vollmed.domain.consulta.DatosCancelamientoConsulta;
import com.johnnybenitez.vollmed.domain.consulta.DatosDetalleConsulta;
import com.johnnybenitez.vollmed.domain.consulta.DatosReservaConsulta;
import com.johnnybenitez.vollmed.domain.consulta.ReservaDeConsultas;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ReservaDeConsultas reserva;

    @Transactional
    @PostMapping
    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsulta datos) {

        var detalleConsulta = reserva.reservar(datos);
        return ResponseEntity.ok(detalleConsulta);

    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelamientoConsulta datos) {
        reserva.cancelar(datos);
        return ResponseEntity.noContent().build();
    }
}
