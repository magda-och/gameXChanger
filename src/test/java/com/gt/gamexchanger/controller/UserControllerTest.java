package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.enums.Role;
import com.gt.gamexchanger.exception.NoExistingUser;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {
    @MockBean
    private UserService userService;

    private UserControllerTest.UnderTest underTest;
    private User testedUser;
    private UserDto testedUserDto;
    private Long testedUserId;

    @BeforeEach
    void setUp() {
        underTest = new UnderTest();

        //creating testing user
        testedUser = new User();
        testedUser.setId(1L);
        testedUser.setFirstName("Jan");
        testedUser.setLastName("Kowalski");
        testedUser.setEmail("jan.kowalski@wp.pl");
        testedUser.setPassword("janek");

        testedUserId = testedUser.getId();

        //creating UserDto
        testedUserDto = new UserDto();
        testedUserDto.setId(1L);
        testedUserDto.setFirstName("Jan");
        testedUserDto.setLastName("Kowalski");
        testedUserDto.setEmail("jan.kowalski@wp.pl");
        testedUserDto.setPassword("janek");
        testedUserDto.setRole(Role.USER);
    }

    @Test
    public void getAllUsers_shouldReturnProperly(){
        List<UserDto> users = new ArrayList<>();
        users.add(testedUserDto);

        when(userService.getAllUsers())
                .thenReturn(List.of(testedUserDto));

        assertEquals(ResponseEntity
                .ok()
                .body(users), underTest.getAllUsers());

    }
    @Test
    public void getAllUsers() {
        underTest.getAllUsers();

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void findUserByName() {
        String firstName = testedUser.getFirstName();
        String lastName = testedUser.getLastName();
        underTest.findUserByName(firstName, lastName);
        verify(userService, times(1)).searchUsers(firstName, lastName);
    }

    @Test
    public void deleteUser() {
        underTest.deleteUser(testedUserId);
        verify(userService, times(1)).deleteUser(testedUserId);
    }

    @Test
    public void updateUser() {
        UserDto newUser = new UserDto();
        newUser.setFirstName("Kaja");

        underTest.updateUser(testedUserId, newUser);

        verify(userService, times(1)).updateUser(testedUserId, newUser);
    }

    @Test
    public void changePassword() {
        String newPassword = "kotek";

        underTest.changePassword(testedUserId, newPassword);

        verify(userService, times(1)).changePassword(testedUserId, newPassword);
    }

    @Test
    public void getAllFriends_whenIsCalled_shouldReturnNotNull() {
        underTest.getFriends(testedUserId);
        assertNotNull(underTest.getFriends(testedUserId));
    }

    @Test
    void getUserById_WhenUserExist_shouldReturnResponseEntityOk() {

        when(userService.getUserById(1L)).thenReturn((Optional.of(testedUserDto)));

        assertEquals(ResponseEntity
                .ok()
                .body(testedUserDto), underTest.getUserById(1L));
    }

    @Test
    public void getAllFriends_whenUserHaveFriend_shouldReturnResponseEntityOk() {
        UserDto friend = new UserDto(
                "Jan",
                "Fasola",
                "jasFasola@com.pl",
                "JFasola",
                "katowice",
                123123123,
                Role.USER);

        List<UserDto> myFriends = new ArrayList<>();
        myFriends.add(friend);

        when(userService.getMyFriends(testedUserId)).thenReturn(myFriends);
        assertEquals(ResponseEntity
                .ok()
                .body(myFriends), underTest.getFriends(testedUserId));
    }

    @Test
    void removeFriend_whenUserHaveFriend_shouldReturnResponseEntityNoContent() {
        UserDto friend = new UserDto(
                2L,
                "Jan",
                "Fasola",
                "jasFasola@com.pl",
                "JFasola",
                "katowice",
                123123123,
                Role.USER);

        List<UserDto> myFriends = new ArrayList<>();
        myFriends.add(friend);
        when(userService.getMyFriends(testedUserId)).thenReturn(myFriends);
        assertEquals(ResponseEntity.noContent().build(), underTest.removeFriend(testedUserId, 2L));
    }

    @Test
    void removeFriend_whenUserHaveNoFriends_shouldReturnResponseNotFound() {
        Long notExistingUserId = 3L;
        doThrow(new NoExistingUser()).when(userService).deleteFriend(notExistingUserId, testedUserId);
        assertEquals(ResponseEntity.notFound().build(), underTest.removeFriend(testedUserId, notExistingUserId));
    }

    @Test
    void getUserByEmail_userExist_shouldFindProperly() {

        when(userService.findUserByEmail(testedUserDto.getEmail())).thenReturn((Optional.of(testedUserDto)));

        assertEquals(ResponseEntity
                .ok()
                .body(testedUserDto), underTest.getUserByEmail("jan.kowalski@wp.pl"));
    }
    @Test
    void getUserByEmail_userIsNull_shouldThrowNoSuchElementException() {

        assertThrows(NoSuchElementException.class, () -> underTest.getUserByEmail(null));

    }

    @Test
    void getUserByEmail_userDoesntExist_shouldThrowNoSuchElementException() {

        assertThrows(NoSuchElementException.class, () -> underTest.getUserByEmail("bla@bla"));

    }

    @Test
    void getNotMyFriends() {
        UserDto notFriend = new UserDto(
                2L,
                "Jan",
                "Fasola",
                "jasFasola@com.pl",
                "JFasola",
                "katowice",
                123123123,
                Role.USER);

        List<UserDto> usersNotFriends = new ArrayList<>();
        usersNotFriends.add(notFriend);
        when(userService.getUsersWhoAreNotMyFriends(testedUserId)).thenReturn(usersNotFriends);
        assertEquals(ResponseEntity
                .ok()
                .body(usersNotFriends), underTest.getNotMyFriends(testedUserId));
    }

    class UnderTest extends UserController {
        public UnderTest() {
            super(userService);
        }
    }

    //testy integracyjne

/*    @Test
    @WithMockUser(roles = "ADMIN")
    public void getAllUsers_beforeAddUser_status200() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/user")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getAllUsers_afterAddUser_shouldReturnProperly() throws Exception {
        when(userService.getAllUsers())
                .thenReturn(List.of(testedUserDto));

        mvc
                .perform(MockMvcRequestBuilders
                        .get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Jan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Kowalski"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("jan.kowalski@wp.pl"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("janek"));

    }*/
}


