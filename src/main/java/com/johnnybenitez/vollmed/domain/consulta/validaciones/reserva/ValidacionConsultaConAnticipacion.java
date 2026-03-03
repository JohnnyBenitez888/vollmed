package com.johnnybenitez.vollmed.domain.consulta.validaciones.reserva;

import com.johnnybenitez.vollmed.domain.ValidacionException;
import com.johnnybenitez.vollmed.domain.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorConsultaConAnticipacionReserva")
public class ValidacionConsultaConAnticipacion implements ValidadorDeConsultas {

    public void validar(DatosReservaConsulta datos){
        var fechaConsulta = datos.fecha();
        var fechaActual = LocalDateTime.now();
        var diferenciaEnMinutos = Duration.between(fechaActual, fechaConsulta).toMinutes();

        if (diferenciaEnMinutos < 30) {
            throw new ValidacionException("Las consultas deben ser agendadas con al menos 30 minutos de anticipación");
        }
    }
}
