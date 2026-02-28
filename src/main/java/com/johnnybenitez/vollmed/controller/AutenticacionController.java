package com.johnnybenitez.vollmed.controller;

import com.johnnybenitez.vollmed.domain.usuario.DatosAutenticacion;
import com.johnnybenitez.vollmed.domain.usuario.Usuario;
import com.johnnybenitez.vollmed.infra.security.DatosTokenJWT;
import com.johnnybenitez.vollmed.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.contrasena());/*Para saber los datos del usuario que está intentando loguearse*/
        var autenticacion = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());/*Es el que le devolvemos al usuario para uso futuro*/
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }

}
