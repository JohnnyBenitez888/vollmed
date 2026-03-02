package com.johnnybenitez.vollmed.domain.consulta.validaciones;

import com.johnnybenitez.vollmed.domain.ValidacionException;
import com.johnnybenitez.vollmed.domain.consulta.DatosReservaConsulta;
import com.johnnybenitez.vollmed.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoActivo implements ValidadorDeConsultas{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosReservaConsulta datos) {
        /*Elección del médico es opcional*/
        if (datos.idMedico() == null) {
            return;
        }

        var medicoEstaActivo = medicoRepository.findActivoById(datos.idMedico());
        if (!medicoEstaActivo) {
            throw new ValidacionException("El médico no existe o no está activo");
        }
    }
}
