package com.miempresa.miaplicacion.users.service;

import com.miempresa.miaplicacion.users.dto.UserCreateRequestDTO;
import com.miempresa.miaplicacion.users.dto.UserResponseDTO;
import com.miempresa.miaplicacion.users.mapper.UserMapper;
import com.miempresa.miaplicacion.users.model.User;
import com.miempresa.miaplicacion.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<UserResponseDTO> getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toResponseDTO);

    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> usersList = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        for(User user : usersList){
            UserResponseDTO userResponseDTO = userMapper.toResponseDTO(user);
            userResponseDTOList.add(userResponseDTO);
        }
        return userResponseDTOList;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO createUser(UserCreateRequestDTO userDTO) {
        User user = userMapper.toEntity(userDTO);

        UserResponseDTO userResponseDTO =userMapper.toResponseDTO(userRepository.save(user));

        return userResponseDTO;
    }

    @Override
    public Optional<UserResponseDTO> updateUser(Long id, UserCreateRequestDTO userDTO) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            return Optional.empty();
        }

        User existing = user. get();
        userMapper.applyUpdates(existing, userDTO);
        User saved = userRepository.save(existing);

        return Optional.of(userMapper.toResponseDTO(saved));
    }
}
