package com.gt.gamexchanger.service;

import com.gt.gamexchanger.enums.Role;
import com.gt.gamexchanger.exception.NoDataFoundException;
import com.gt.gamexchanger.exception.NoExistingUser;
import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.dto.UserDto;

import com.gt.gamexchanger.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DtoMapper<UserDto, User> dtoMapper;

/*    public UserService(UserRepository userRepository, DtoMapper<UserDto, User> dtoMapper) {
        this.userRepository = userRepository;
        this.dtoMapper = dtoMapper;
    }*/

    public UserDto addUser(UserDto userDto) {

        if (areFieldsInRegistrationFilledCorrectly(userDto)) {
            User user = dtoMapper.toDomainObject(userDto);

           if (user.getRole() == null) {
               user.setRole(Role.USER);
            }

            userRepository.save(user);
            return dtoMapper.toDto(user);
        } else {
            throw new NoDataFoundException();
        }
    }

    public boolean areFieldsInRegistrationFilledCorrectly(UserDto userDto) {
        return (userDto.getFirstName() != null) && (userDto.getLastName() != null)
                && (userDto.getEmail() != null) && (userDto.getPassword() != null);
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
        HashSet<User> userResults = userRepository.searchUsersByFirstNameAndLastName(firstName, lastName);
        userResults.addAll(userRepository.searchUsersByLastName(lastName));
        userResults.addAll(userRepository.searchUsersByFirstName(firstName));

        return userResults.stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public void changeUserFields(User modifiedUser, UserDto newUserDto) {
        if (newUserDto.getFirstName() != null) {
            modifiedUser.setFirstName(newUserDto.getFirstName());
        }
        if (newUserDto.getLastName() != null) {
            modifiedUser.setLastName(newUserDto.getLastName());
        }
    }

    public void updateUser(Long userId, UserDto newUserDto) {
        var userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            var modifiedUser = userOptional.get();
            changeUserFields(modifiedUser, newUserDto);
            userRepository.save(modifiedUser);
        } else {
            throw new NoExistingUser();
        }
    }

    public void changePassword(Long userId, String newPassword) {
        var userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            var modifiedUser = userOptional.get();
            modifiedUser.setPassword(newPassword);
            userRepository.save(modifiedUser);
        } else {
            throw new NoExistingUser();
        }
    }

    public Optional<UserDto> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).map(dtoMapper::toDto);
    }

    public List<UserDto> getMyFriends(Long userId) {
        var userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new NoExistingUser();
        }
        List<User> myFriends = userOptional.get().getFriends();
        return myFriends.stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteFriend(Long userId, Long friendId) {
        var userOptional = userRepository.findById(userId);
        var friendOptional = userRepository.findById(friendId);
        if(userOptional.isEmpty() || friendOptional.isEmpty()){
            throw new NoExistingUser();
        }
        userOptional.get().getFriends().remove(friendOptional.get());
        friendOptional.get().getFriends().remove(userOptional.get());
        userRepository.save(userOptional.get());
        userRepository.save(friendOptional.get());
    }
}





