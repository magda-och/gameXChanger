package com.gt.gamexchanger.service;

import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.exception.NoExistingUser;
import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@MockitoSettings(strictness = Strictness.LENIENT)

public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private DtoMapper<UserDto, User> dtoMapper;
    private User testingUser;
    private List<User> users;
    private UserDto testingUserDto;
    private List<UserDto> usersDto;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, dtoMapper);

        //creating testing user
        testingUser = new User();
        testingUser.setId(1L);
        testingUser.setFirstName("Jan");
        testingUser.setLastName("Kowalski");
        testingUser.setEmail("jan.kowalski@wp.pl");
        testingUser.setPassword("janek");

        //creating list with users
        users = Arrays.asList(testingUser);

        //creating UserDto
        testingUserDto = new UserDto();
        testingUserDto.setId(1L);
        testingUserDto.setFirstName("Jan");
        testingUserDto.setLastName("Kowalski");
        testingUserDto.setEmail("jan.kowalski@wp.pl");
        testingUserDto.setPassword("janek");

        //creating list with Users Dto
        usersDto = Arrays.asList(testingUserDto);
    }

    @Test
    void getAllUsers_userAdded_userShouldBeReturned() {

        when(userRepository.findAll()).thenReturn(users);
        when(dtoMapper.toDto(testingUser)).thenReturn(testingUserDto);

        assertEquals(usersDto, userService.getAllUsers());

        verify(userRepository, times(1)).findAll();
        verify(dtoMapper, times(1)).toDto(Mockito.isA(User.class));
    }

    @Test
    void getUserById_userAdded_shouldFindProperly() {

        when(userRepository.findById(1L)).thenReturn(Optional.of(testingUser)); //testedUser
        when(dtoMapper.toDto(testingUser)).thenReturn(testingUserDto);

        assertEquals(testingUserDto, userService.getUserById(1L).get()); // konwencja taka sama ma być given when then
        //  model i dto moze byc tworzony, reszta mock
        // współna konwencja do nazw testów do endpointów, given when then itd...
        // czyletniejsze nazwy zmiennych
    }

    @Test
    void searchUsers_userAdded_shouldSearchUser() {
        String firstNameToSearch = testingUser.getFirstName();
        String lastNameToSearch = testingUser.getLastName();

        when(userRepository.searchUsersByFirstNameAndLastName(firstNameToSearch,
                lastNameToSearch)).thenReturn(users);

        when(dtoMapper.toDto(testingUser)).thenReturn(testingUserDto);

        assertEquals(usersDto, userService.searchUsers(firstNameToSearch, lastNameToSearch));
    }

    @Test
    void addUser_userAdded_shouldReturnCorrectUser() {
        when(userRepository.save(testingUser)).thenReturn(testingUser);

        when(dtoMapper.toDomainObject(testingUserDto)).thenReturn(testingUser);
        when(dtoMapper.toDto(testingUser)).thenReturn(testingUserDto);

        UserDto result = userService.addUser(testingUserDto);

        assertTrue(testingUserDto.getPassword().equals(result.getPassword()));
    }

    @Test
    void deleteUser_userAdded_shouldBeDeletedCorrectly() {
        when(userRepository.findById(testingUser.getId())).thenReturn(Optional.of(testingUser));
        when(dtoMapper.toDto(testingUser)).thenReturn(testingUserDto);

        userService.deleteUser(testingUserDto.getId());

        verify(userRepository).deleteById(testingUser.getId());
    }

    @Test
    public void should_throw_exception_when_user_doesnt_exist() {
        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(null));

        assertThrows(
                NoExistingUser.class,
                () -> userService.deleteUser(3L),
                "User doesn't exist"
        );
    }
}

//    @Test
//    public void updateUser_userFieldChanged_shouldBeChanged(){
//        UserDto newUser = new UserDto();
//        newUser.setFirstName("Magda");
//        given(userRepository.findById(testingUser.getId())).willReturn(Optional.of(testingUser));
//        userService.updateUser(testingUser.getId(), newUser);
//
//        assertEquals("Magda", testingUser.getFirstName());
//    }
//
//    }

