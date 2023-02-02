package com.gt.gamexchanger.service;

import com.gt.gamexchanger.exception.NoDataFoundException;
import com.gt.gamexchanger.exception.NoExistingUser;
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
        if (areFieldsInRegistrationFilledCorrectly(userDto)) {
            User user = dtoMapper.toDomainObject(userDto);
            userRepository.save(user);
            return dtoMapper.toDto(user);
        } else {
            throw new NoDataFoundException();
        }
    }

    public boolean areFieldsInRegistrationFilledCorrectly(UserDto userDto) {
        if ((userDto.getFirstName() == null) || (userDto.getLastName() == null)
                || (userDto.getEmail() == null) || (userDto.getPassword() == null)) {
            return false;
        }
        return true;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id).map(dtoMapper::toDto);
    }

    public void deleteUser(Long id) {
        if (getUserById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new NoExistingUser();
        }
    }

    public List<UserDto> searchUsers(String firstName, String lastName) {
        List<User> userResults = userRepository.searchUsersByFirstNameAndLastName(firstName, lastName);
        userResults.addAll(userRepository.searchUsersByLastName(lastName));
        userResults.addAll(userRepository.searchUsersByFirstName(firstName));

        return userResults.stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public void changeUserFields(UserDto newUserDto, User modifiedUser) {
        if (newUserDto.getFirstName() != null) {
            modifiedUser.setFirstName(newUserDto.getFirstName());
        }
        if (newUserDto.getLastName() != null) {
            modifiedUser.setLastName(newUserDto.getLastName());
        }
    }

    public void updateUser(Long userId, UserDto newUserDto) {
        var userOptional = userRepository.findUserById(userId);
        if (userOptional.isPresent()) {
            var modifiedUser = userOptional.get();
            changeUserFields(newUserDto, modifiedUser);
            userRepository.save(modifiedUser);
        } else {
            throw new NoExistingUser();
        }
    }

    public void changePassword(Long userId, String newPassword) {
        var userOptional = userRepository.findUserById(userId);
        if (userOptional.isPresent()) {
            var modifiedUser = userOptional.get();
            modifiedUser.setPassword(newPassword);
            userRepository.save(modifiedUser);
        } else {
            throw new NoExistingUser();
        }
    }
}





