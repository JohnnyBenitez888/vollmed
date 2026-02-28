package com.johnnybenitez.vollmed.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.johnnybenitez.vollmed.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {


    public String generarToken(Usuario user) {
        try {
            var algoritmo = Algorithm.HMAC256("12345678");
            return JWT.create()
                    .withIssuer("API voll.med")
                    .withSubject(user.getLogin())
                    .withExpiresAt(fechaExpiracion())
                    /*.withClaim("id", user.getId())*/
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
           throw new RuntimeException("Error al generar el token JWT", exception);
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
