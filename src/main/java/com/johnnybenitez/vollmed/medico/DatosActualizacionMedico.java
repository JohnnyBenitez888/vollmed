package com.johnnybenitez.vollmed.medico;

import com.johnnybenitez.vollmed.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionMedico(
            @NotNull Long id,
            String nombre,
            String telefono,
            DatosDireccion direccion
) {
}
