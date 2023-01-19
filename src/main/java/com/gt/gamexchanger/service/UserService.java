package com.gt.gamexchanger.service;

import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final DtoMapper<UserDto, User> dtoMapper;

    public UserService(UserRepository userRepository, DtoMapper<UserDto, User> dtoMapper) {
        this.userRepository = userRepository;
        this.dtoMapper = dtoMapper;
    }

    public UserDto addUser(UserDto userDto) {
        var user = dtoMapper.toDomainObject(userDto);
        userRepository.addUser(user);
        return dtoMapper.toDto(user);

    }

    public List<UserDto> getAllUsers() {
        return userRepository.getAllUsers().stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> findUserByName(String name) {
        return userRepository.findUserByName(name).stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public boolean deleteUser(Long id) {
        if (getUserById(id).isPresent()) {
            userRepository.deleteUser(id);
            return true;
        }
        return false;
    }

    public User updateUserFields(UserDto userDto, User user) {
        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }
        return user;
    }

    public void updateUser(Long userId, UserDto userDto) {
        var userOptional = getUserById(userId);
        if (userOptional.isPresent()) {
            var user = userOptional.get();
            updateUserFields(userDto, user);
        }
    }

    public void changePassword(Long userId, String newPassword) {
        var userOptional = getUserById(userId);
        if (userOptional.isPresent()) {
            var user = userOptional.get();
            user.setPassword(newPassword);
        }
    }
}





