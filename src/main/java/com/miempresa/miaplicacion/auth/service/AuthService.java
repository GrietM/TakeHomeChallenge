package com.miempresa.miaplicacion.auth.service;

import com.miempresa.miaplicacion.auth.dto.LoginRequestDTO;
import com.miempresa.miaplicacion.auth.dto.LoginResponseDTO;
import com.miempresa.miaplicacion.auth.dto.RegisterRequestDTO;
import com.miempresa.miaplicacion.users.dto.UserResponseDTO;


public interface AuthService {
    UserResponseDTO register(RegisterRequestDTO request);
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}