package com.johnnybenitez.vollmed.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacion(
        String login,
        String contrasena
) {
}
