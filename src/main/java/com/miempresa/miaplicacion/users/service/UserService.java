package com.miempresa.miaplicacion.users.service;

import com.miempresa.miaplicacion.users.dto.UserUpadteRequestDTO;
import com.miempresa.miaplicacion.users.dto.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
     Optional<UserResponseDTO> getUserById(Long id);
     List<UserResponseDTO> getAllUsers();
     void deleteUserById(Long id);
     Optional<UserResponseDTO> updateUser(Long id, UserUpadteRequestDTO user);
}
