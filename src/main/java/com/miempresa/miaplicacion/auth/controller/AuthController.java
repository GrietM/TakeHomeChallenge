package com.miempresa.miaplicacion.auth.controller;

import com.miempresa.miaplicacion.auth.dto.LoginRequestDTO;
import com.miempresa.miaplicacion.auth.dto.LoginResponseDTO;
import com.miempresa.miaplicacion.auth.dto.RegisterRequestDTO;
import com.miempresa.miaplicacion.auth.service.AuthService;
import com.miempresa.miaplicacion.users.dto.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        UserResponseDTO created = authService.register(registerRequestDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping ("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        LoginResponseDTO loginResponseDTO = authService.login(loginRequestDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }
}
