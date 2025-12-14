package com.miempresa.miaplicacion.users.mapper;

import com.miempresa.miaplicacion.users.dto.UserCreateRequestDTO;
import com.miempresa.miaplicacion.users.dto.UserResponseDTO;
import com.miempresa.miaplicacion.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toResponseDTO (User user){
        UserResponseDTO userDTO = new UserResponseDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }

    public User toEntity (UserCreateRequestDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());

        return user;
    }

    public void applyUpdates(User existing, UserCreateRequestDTO dto) {
        existing.setName(dto.getName());
        existing.setLastname(dto.getLastname());
        existing.setEmail(dto.getEmail());
    }

}
