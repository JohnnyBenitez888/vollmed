package com.johnnybenitez.vollmed.domain.direccion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosDireccion(
        @NotBlank String calle,
        String numero,
        String complemento,
        @NotBlank String barrio,
        @NotBlank @Pattern(regexp = "\\d{4}") String codigo_postal,
        @NotBlank String ciudad,
        @NotBlank String estado
) {
    public DatosDireccion(@NotBlank String calle, String numero, String complemento, String barrio, String codigo_postal, String ciudad, String estado) {
        this.calle = calle;
        this.numero = numero;
        this.complemento = complemento;
        this.barrio = barrio;
        this.codigo_postal = codigo_postal;
        this.ciudad = ciudad;
        this.estado = estado;
    }
}
