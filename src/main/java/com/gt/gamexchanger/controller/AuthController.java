package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.enums.Role;
import com.gt.gamexchanger.payload.request.LoginRequest;
import com.gt.gamexchanger.payload.response.AuthenticationResponse;
import com.gt.gamexchanger.security.services.JwtService;
import com.gt.gamexchanger.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody UserDto signUpRequest) {

        if (userService.findUserByEmail(signUpRequest.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new AuthenticationResponse("Error: Email is already in use!"));
        }
        Role role = addRole(signUpRequest);

        UserDto userToRegister = new UserDto(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                signUpRequest.getCity(),
                signUpRequest.getPhoneNumber(),
                role);

        var jwtToken = jwtService.generateToken(userToRegister);
        userService.addUser(userToRegister);
        System.out.println(AuthenticationResponse.builder().token(jwtToken).build().getToken());

        return ResponseEntity.ok(AuthenticationResponse.builder().token(jwtToken).build());
    }


    @PostMapping("/login") //do authentication controller
    public ResponseEntity<AuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        var user = userService.findUserByEmail(loginRequest.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(AuthenticationResponse.builder().token(jwtToken).build());
    }

    private static Role addRole(UserDto signUpRequest) {
        Role role;
        String adminEmail = "adminek@admin.com";
        if(signUpRequest.getEmail().equals(adminEmail)){
            role = Role.ADMIN;
        }else{
            role = Role.USER;
        }
        return role;
    }
}
