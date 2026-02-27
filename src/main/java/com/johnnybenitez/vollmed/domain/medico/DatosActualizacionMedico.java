package com.johnnybenitez.vollmed.domain.medico;

import com.johnnybenitez.vollmed.domain.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionMedico(
            @NotNull Long id,
            String nombre,
            String telefono,
            DatosDireccion direccion
) {
}
