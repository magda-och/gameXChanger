package com.gt.gamexchanger.service;

import com.gt.gamexchanger.mapper.UserDtoMapper;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.repository.UserRepositoryImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {

    UserService userService = new UserService(new UserRepositoryImp(), new UserDtoMapper());
    User user1;
    User user2;

    @BeforeEach
    void setUp() {

        user1 = new User("Jan", "Kowalski", "janek@gmail.com", "123");
        user2 = new User("Maja", "Wolska", "majka@gmail.com", "majka");}

    @Test
    public void addUser_userAdded_shouldReturnUserCorrectDto() {

        UserDto userDto = new UserDto(user1.getName(), user1.getLastName(), user1.getEmail(), user1.getPassword());

        UserDto createdUser = userService.addUser(userDto);

        assertEquals(userDto.getName(), createdUser.getName());
    }

    @Test
    void findUserByName_userAdded_shouldReturnUserByName(){
        UserDto userDto = new UserDto(user1.getName(), user1.getLastName(), user1.getEmail(), user1.getPassword());
        userService.addUser(userDto);

        userService.findUserByName("Jan");

        assertEquals(userDto.getName(), userService.getAllUsers().get(0).getName());
    }

}

