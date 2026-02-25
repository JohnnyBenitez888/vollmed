package com.johnnybenitez.vollmed.medico;

import com.johnnybenitez.vollmed.direccion.DatosDirecion;

public record DatosRegistroMedico(
        String nombre,
        String email,
        String documento,
        Especialidad especialidad,
        DatosDirecion direccion
) {
}
