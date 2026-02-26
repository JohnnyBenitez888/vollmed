package com.johnnybenitez.vollmed.paciente;


import com.johnnybenitez.vollmed.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroPaciente(
        @NotBlank String nombre,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "\\d{9,11}") String telefono,
        @NotBlank @Pattern(regexp = "\\d{7,9}") String documento,
        @NotNull @Valid DatosDireccion direccion
) {
}

