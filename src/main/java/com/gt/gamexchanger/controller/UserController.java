package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.payload.request.LoginRequest;
import com.gt.gamexchanger.payload.response.JwtResponse;
import com.gt.gamexchanger.payload.response.MessageResponse;
import com.gt.gamexchanger.security.jwt.JwtUtils;
import com.gt.gamexchanger.security.services.UserDetailsImpl;
import com.gt.gamexchanger.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    final AuthenticationManager authenticationManager;
    final PasswordEncoder passwordEncoder;

    final JwtUtils jwtUtils;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto signUpRequest) {

        if (userService.findUserByEmail(signUpRequest.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        UserDto userToRegister = new UserDto(signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                signUpRequest.getCity(),
                signUpRequest.getPhoneNumber());

        userService.addUser(userToRegister);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/name")
    public @ResponseBody List<UserDto> findUserByName(@RequestParam(value = "firstName", required = false) String firstName,
                                                         @RequestParam(value = "lastName", required = false) String lastName) {
        return userService.searchUsers(firstName, lastName);
        // podzielic na kilka endpointow - /name, /lastname // searchingengine
        // ew konkatenacja imienia i nazwiska
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User successfully removed!");
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        userService.updateUser(userId, userDto);
        return ResponseEntity.ok("User successfully updated!");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{userId}/password")
    public ResponseEntity<?> changePassword(@PathVariable Long userId, @RequestBody String newPassword) {
        userService.changePassword(userId, newPassword);
        return ResponseEntity.ok("Password changed!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
    @GetMapping("/friends/{userId}")
    public ResponseEntity<List<UserDto>> getFriends(@PathVariable("userId") Long userId){
        List<UserDto> myFriends = userService.getMyFriends(userId);
        return new ResponseEntity<>(myFriends, HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId){
        Optional<UserDto> user = userService.getUserById(userId);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @DeleteMapping("friends/{userId}/{friendId}")
    public ResponseEntity<?> remove(@PathVariable Long friendId, @PathVariable Long userId) {
        try{
            userService.deleteFriend(userId,friendId);
            return ResponseEntity.noContent().build();
        }
        catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }
}
