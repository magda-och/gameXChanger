package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.payload.response.AuthenticationResponse;
import com.gt.gamexchanger.security.services.JwtService;
import com.gt.gamexchanger.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private JwtService jwtService;
    private AuthControllerTest.UnderTest underTest;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        underTest = new AuthControllerTest.UnderTest();

        //creating testing user
        User testedUser = new User();
        testedUser.setId(1L);
        testedUser.setFirstName("Jan");
        testedUser.setLastName("Kowalski");
        testedUser.setEmail("jan.kowalski@wp.pl");
        testedUser.setPassword("janek");

        //creating UserDto
        UserDto testedUserDto = new UserDto();
        testedUserDto.setFirstName("Jan");
        testedUserDto.setLastName("Kowalski");
        testedUserDto.setEmail("jan.kowalski@wp.pl");
        testedUserDto.setPassword("janek");
        testedUserDto.setCity("London");
    }

    /*    @Test
    public void addUser_userAdded_shouldBeSaved() {
        underTest.registerUser(testedUserDto);

        verify(userService, times(1)).addUser(testedUserDto);
    }*/

    @Test
    void registerUser_userAdded_NotNull() {
        //given
        UserDto signUpUser = new UserDto(
                "Jan",
                "Kowalski",
                "jan.kowalski@wp.pl",
                "janek",
                "Katowice",
                123123123,
                null);
        underTest.registerUser(signUpUser);
        assertNotNull(underTest.registerUser(signUpUser));
    }

    @Test
    void registerUser_userAdded_WithNull() {
        assertEquals(ResponseEntity
                .badRequest()
                .body( new AuthenticationResponse("User is null!")), underTest.registerUser(null));
    }

/*    @Test
    public void registerUser_userAdded_EmailExist() {
        UserDto signUpUser = new UserDto(
                "Jan",
                "Kowalski",
                "jan.kowalski@wp.pl",
                "janek",
                "Katowice",
                123123123,
                null);

        verify(underTest.registerUser(signUpUser));
    }*/

    @Test
    void authenticateUser() {
    }

    class UnderTest extends AuthController {
        public UnderTest() {
            super(userService, authenticationManager, passwordEncoder, jwtService);
        }
    }
}