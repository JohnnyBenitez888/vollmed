package com.johnnybenitez.vollmed.direccion;

public record DatosDirecion(
        String calle,
        String numero,
        String complemento,
        String barrio,
        String codigo_postal,
        String ciudad,
        String estado
) {
}
