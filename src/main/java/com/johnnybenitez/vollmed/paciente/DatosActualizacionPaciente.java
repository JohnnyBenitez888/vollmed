package com.johnnybenitez.vollmed.paciente;

import com.johnnybenitez.vollmed.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionPaciente(
        @NotNull Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion
) {
}
