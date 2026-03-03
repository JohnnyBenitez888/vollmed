package com.johnnybenitez.vollmed.domain.consulta.validaciones.reserva;

import com.johnnybenitez.vollmed.domain.ValidacionException;
import com.johnnybenitez.vollmed.domain.consulta.ConsultaRepository;
import com.johnnybenitez.vollmed.domain.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConOtraConsultaEnElMismoHorario implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosReservaConsulta datos) {
        var medicoTieneOtraConsultaEnElMismoHorario = consultaRepository.existsByMedicoIdAndFechaAndMotivoCancelamientoIsNull(datos.idMedico(),datos.fecha());

        if (medicoTieneOtraConsultaEnElMismoHorario) {
            throw new ValidacionException("El médico ya tiene otra consulta programada para el mismo horario");
        }
    }
}
