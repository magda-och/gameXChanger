package com.gt.gamexchanger.controller;


import com.gt.gamexchanger.dto.UserDto;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;
    private UnderTest underTest;
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
    }

    @Test
    public void getAllUsers_beforeAddUser_status200() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/user")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void getAllUsers_afterAddUser_shouldReturnProperly() throws Exception {
        when(userService.getAllUsers())
                .thenReturn(List.of(testedUserDto));

        mvc
                .perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Jan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Kowalski"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("jan.kowalski@wp.pl"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("janek"));

    }

    @Test
    public void addUser_userAdded_shouldBeSaved() {
        underTest.addUser(testedUserDto);

        verify(userService, times(1)).addUser(testedUserDto);
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

    class UnderTest extends UserController {
        public UnderTest() {
            super(userService);
        }
    }
}


