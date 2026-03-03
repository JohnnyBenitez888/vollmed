package com.johnnybenitez.vollmed.domain.consulta.validaciones.reserva;

import com.johnnybenitez.vollmed.domain.ValidacionException;
import com.johnnybenitez.vollmed.domain.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

@Component
public class ValidacionFueraHorarioConsulta implements ValidadorDeConsultas {

    public void validar(DatosReservaConsulta datos){
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().getValue() == 7;
        var horarioAntesAperturaClinica = fechaConsulta.getHour() < 7;
        var horarioDespuesCierreClinica = fechaConsulta.getHour() > 18;

        if (domingo || horarioAntesAperturaClinica || horarioDespuesCierreClinica) {
            throw new ValidacionException("La consulta debe ser agendada dentro del horario de atención de la clínica");
        }
    }
}
