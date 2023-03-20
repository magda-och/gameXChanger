package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.payload.request.LoginRequest;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

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

    private UserDto testedUserDto;
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
        testedUserDto = new UserDto();
        testedUserDto.setFirstName("Jan");
        testedUserDto.setLastName("Kowalski");
        testedUserDto.setEmail("jan.kowalski@wp.pl");
        testedUserDto.setPassword("janek");
        testedUserDto.setCity("Katowice");
        testedUserDto.setPhoneNumber(123123123);

        /*mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSessionRepositoryFilter)
                .apply(springSecurity())
                .build();*/
    }

    /*    @Test
    public void addUser_userAdded_shouldBeSaved() {
        underTest.registerUser(testedUserDto);

        verify(userService, times(1)).addUser(testedUserDto);
    }*/

    @Test
    void registerUser_afterUserRegisterProperly_NotNull() {

        underTest.registerUser(testedUserDto);
        assertNotNull(underTest.registerUser(testedUserDto));
    }

    @Test
    void registerUser_afterUserRegisterProperly_ShouldResponseOK() {
        underTest.registerUser(testedUserDto);
        assertEquals(ResponseEntity
                .ok(AuthenticationResponse
                        .builder()
                        .token(jwtService.generateToken(testedUserDto))
                        .build()), underTest.registerUser(testedUserDto));
    }

    @Test
    void registerUser_afterUserRegisterWithNullSignUp_ShouldResponseBadRequest() {
        assertEquals(ResponseEntity
                .badRequest()
                .body( new AuthenticationResponse("User is null!")), underTest.registerUser(null));
    }

    @Test
    public void registerUser_afterUserRegisterProperlyButEmailExist_ShouldResponseBadRequest() {

        when(userService.findUserByEmail(testedUserDto.getEmail())).thenReturn(Optional.of(testedUserDto));

        assertEquals(ResponseEntity
                .badRequest()
                .body( new AuthenticationResponse("Error: Email is already in use!")), underTest.registerUser(testedUserDto));
    }

    @Test
    void registerUser_afterUserRegisterProperlyAsAdmin_ShouldResponseOK() {
        UserDto admin = new UserDto("Admin", "Admin", "admin@admin.com", "adminPassword", null, 0, null);

        underTest.registerUser(admin);

        assertEquals(ResponseEntity
                .ok(AuthenticationResponse
                        .builder()
                        .token(jwtService.generateToken(admin))
                        .build()), underTest.registerUser(admin));
    }

    @Test
    void authenticateUser_afterLogin_shouldReturnProperly() {

        LoginRequest loginRequest = new LoginRequest("jan.kowalski@wp.pl", "janek");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword());


        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(token);
        SecurityContextHolder.setContext(securityContext);

        when(userService.findUserByEmail(loginRequest.getEmail())).thenReturn(Optional.of(testedUserDto));


        assertEquals(ResponseEntity
                .ok(AuthenticationResponse
                        .builder()
                        .token(jwtService.generateToken(testedUserDto))
                        .build()),
                underTest.authenticateUser(loginRequest));

    }

    class UnderTest extends AuthController {
        public UnderTest() {
            super(userService, authenticationManager, passwordEncoder, jwtService);
        }
    }
}