package com.gt.gamexchanger.service;

import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final DtoMapper<UserDto, User> dtoMapper;

    public UserService(UserRepository userRepository, DtoMapper<UserDto, User> dtoMapper) {
        this.userRepository = userRepository;
        this.dtoMapper = dtoMapper;
    }

    public UserDto addUser(UserDto userDto){
        var user =  dtoMapper.toDomainObject(userDto);
        userRepository.addUser(user);
        return dtoMapper.toDto(user);

    }

    public List<UserDto> getAllUsers() {
        return userRepository.getAllUsers().stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> findUserByFullName(String name, String lastName){
        return userRepository.findUserByFullName(name,lastName).stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> findUserByFirstName(String firstName){
        return userRepository.findUserByFirstName(firstName).stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> findUserByLastName(String lastName){
        return userRepository.findUserByLastName(lastName).stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }




}
