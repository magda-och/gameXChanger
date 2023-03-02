package com.gt.gamexchanger.controller;
import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.enums.Role;
import com.gt.gamexchanger.payload.request.LoginRequest;
import com.gt.gamexchanger.payload.response.AuthenticationResponse;
import com.gt.gamexchanger.security.services.JwtService;
import com.gt.gamexchanger.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

/*    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }*/

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/auth/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody UserDto signUpRequest) {

        if (userService.findUserByEmail(signUpRequest.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new AuthenticationResponse("Error: Email is already in use!"));
        }

        UserDto userToRegister = new UserDto(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                signUpRequest.getCity(),
                signUpRequest.getPhoneNumber(),
                Role.USER);

        var jwtToken = jwtService.generateToken(userToRegister);
        userService.addUser(userToRegister);
        System.out.println(AuthenticationResponse.builder().token(jwtToken).build().getToken());

        return ResponseEntity.ok(AuthenticationResponse.builder().token(jwtToken).build());
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

    @PostMapping("/auth/login")
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
    @GetMapping("/friends/{userId}")
    public ResponseEntity<List<UserDto>> getFriends(@PathVariable("userId") Long userId){
        List<UserDto> myFriends = userService.getMyFriends(userId);
        return new ResponseEntity<>(myFriends, HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId){
        Optional<UserDto> user = userService.getUserById(userId);
        return new ResponseEntity<>(user.orElseThrow(), HttpStatus.OK);
    }

    @DeleteMapping("friends/{userId}/{friendId}")
    public ResponseEntity<?> remove(@PathVariable Long friendId,
                                    @PathVariable Long userId) {
        try{
            userService.deleteFriend(userId,friendId);
            return ResponseEntity.noContent().build();
        }
        catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/me/{userEmail}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String userEmail){
        Optional<UserDto> user = userService.findUserByEmail(userEmail);
        return new ResponseEntity<>(user.orElseThrow(), HttpStatus.OK);
    }
}
