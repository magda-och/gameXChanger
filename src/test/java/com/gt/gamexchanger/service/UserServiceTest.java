package com.gt.gamexchanger.service;

import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.exception.NoExistingUser;
import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.repository.RoleRepository;
import com.gt.gamexchanger.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
    RoleRepository roleRepository;
    @Mock
    private DtoMapper<UserDto, User> dtoMapper;
    private User testedUser;
    private List<User> users;
    private UserDto testedUserDto;
    private List<UserDto> usersDto;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, dtoMapper, roleRepository);

        //creating testing user
        testedUser = new User();
        testedUser.setId(1L);
        testedUser.setFirstName("Jan");
        testedUser.setLastName("Kowalski");
        testedUser.setEmail("jan.kowalski@wp.pl");
        testedUser.setPassword("janek");

        //creating list with users
        users = Arrays.asList(testedUser);

        //creating UserDto
        testedUserDto = new UserDto();
        testedUserDto.setId(1L);
        testedUserDto.setFirstName("Jan");
        testedUserDto.setLastName("Kowalski");
        testedUserDto.setEmail("jan.kowalski@wp.pl");
        testedUserDto.setPassword("janek");

        //creating list with Users Dto
        usersDto = Arrays.asList(testedUserDto);
    }

    @Test
    void getAllUsers_userAdded_userShouldBeReturned() {

        when(userRepository.findAll()).thenReturn(users);
        when(dtoMapper.toDto(testedUser)).thenReturn(testedUserDto);

        assertEquals(usersDto, userService.getAllUsers());

        verify(userRepository, times(1)).findAll();
        verify(dtoMapper, times(1)).toDto(Mockito.isA(User.class));
    }

    @Test
    void getUserById_userAdded_shouldFindProperly() {

        when(userRepository.findById(1L)).thenReturn(Optional.of(testedUser)); //testedUser
        when(dtoMapper.toDto(testedUser)).thenReturn(testedUserDto);

        assertEquals(testedUserDto, userService.getUserById(1L).get()); // konwencja taka sama ma być given when then
        //  model i dto moze byc tworzony, reszta mock
        // współna konwencja do nazw testów do endpointów, given when then itd...
        // czyletniejsze nazwy zmiennych
    }

    @Test
    void searchUsers_userAdded_shouldSearchUser() {
        String firstNameToSearch = testedUser.getFirstName();
        String lastNameToSearch = testedUser.getLastName();

        when(userRepository.searchUsersByFirstNameAndLastName(firstNameToSearch,
                lastNameToSearch)).thenReturn((HashSet<User>) users);

        when(dtoMapper.toDto(testedUser)).thenReturn(testedUserDto);

        assertEquals(usersDto, userService.searchUsers(firstNameToSearch, lastNameToSearch));
    }

    @Test
    void addUser_userAdded_shouldReturnCorrectUser() {
        when(userRepository.save(testedUser)).thenReturn(testedUser);

        when(dtoMapper.toDomainObject(testedUserDto)).thenReturn(testedUser);
        when(dtoMapper.toDto(testedUser)).thenReturn(testedUserDto);

        UserDto result = userService.addUser(testedUserDto);

        assertTrue(testedUserDto.getPassword().equals(result.getPassword()));
    }

    @Test
    void deleteUser_userAdded_shouldBeDeletedCorrectly() {
        when(userRepository.findById(testedUser.getId())).thenReturn(Optional.of(testedUser));
        when(dtoMapper.toDto(testedUser)).thenReturn(testedUserDto);

        userService.deleteUser(testedUserDto.getId());

        verify(userRepository).deleteById(testedUser.getId());
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

    @Test
    public void changePassword_newPasswordGiven_shouldChangeCorrectly() {
        given(userRepository.findById(testedUser.getId())).willReturn(Optional.of(testedUser));
        userService.changePassword(testedUser.getId(), "kotek");

        assertTrue(testedUser.getPassword().equals("kotek"));

    }

    @Test
    public void updateUser_userFieldChanged_shouldBeChangedCorrectly() {
        UserDto newUser = new UserDto();
        newUser.setFirstName("Magda");

        given(userRepository.findById(testedUser.getId())).willReturn(Optional.of(testedUser));
        userService.updateUser(testedUser.getId(), newUser);

        assertTrue(testedUser.getFirstName().equals("Magda"));
        verify(userRepository).save(testedUser);

    }

}



