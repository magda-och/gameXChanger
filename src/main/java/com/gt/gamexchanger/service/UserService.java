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
        }
        throw new NoDataFoundException();
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
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        if (getUserById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new NoExistingUser();
        }
    }


//    public User updateUserFields(UserDto userDto, User user) {
//        if (userDto.getFirstName() != null) {
//            user.setFirstName(userDto.getFirstName());
//        }
//        if (userDto.getLastName() != null) {
//            user.setLastName(userDto.getLastName());
//        }
//        return user;
//    }
//
//    public boolean updateUser(Long userId, UserDto userDto) {
//        var userOptional = getUserById(userId);
//        if (userOptional.isPresent()) {
//            var user = userOptional.get();
//            updateUserFields(userDto, user);
//            return true;
//        }
//        return false;
//    }
//
//    public boolean changePassword(Long userId, String newPassword) {
//        var userOptional = getUserById(userId);
//        if (userOptional.isPresent()) {
//            var user = userOptional.get();
//            user.setPassword(newPassword);
//            return true;
//        }
//        return false;
//    }
//}

}





