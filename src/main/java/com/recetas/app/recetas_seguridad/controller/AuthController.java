package com.recetas.app.recetas_seguridad.controller;

import com.recetas.app.recetas_seguridad.dto.LoginRequest;
import com.recetas.app.recetas_seguridad.dto.LoginResponse;
import com.recetas.app.recetas_seguridad.model.Usuario;
import com.recetas.app.recetas_seguridad.repository.UsuarioRepository;
import com.recetas.app.recetas_seguridad.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(loginRequest.getUsername());

        if (usuario.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), usuario.get().getPassword())) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        String token = jwtService.generateToken(usuario.get().getUsername(), usuario.get().getRole());
        LoginResponse response = new LoginResponse(token, usuario.get().getUsername(), usuario.get().getRole());

        return ResponseEntity.ok(response);
    }
}
