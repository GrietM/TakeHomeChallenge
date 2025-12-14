package com.miempresa.miaplicacion.users.service;

import com.miempresa.miaplicacion.users.dto.UserCreateRequestDTO;
import com.miempresa.miaplicacion.users.dto.UserResponseDTO;
import com.miempresa.miaplicacion.users.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
     Optional<UserResponseDTO> getUserById(Long id);
     List<UserResponseDTO> getAllUsers();
     void deleteUserById(Long id);
     UserResponseDTO createUser(UserCreateRequestDTO user);
     Optional<UserResponseDTO> updateUser(Long id, UserCreateRequestDTO user);
}
