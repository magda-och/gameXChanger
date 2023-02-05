package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    // todo add user- only with unique email
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return ResponseEntity.ok("User created!");
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
    @PatchMapping("/password/{userId}") // zmienić kolejność password i userId
    public ResponseEntity<?> changePassword(@PathVariable Long userId, @RequestBody String newPassword) {
        userService.changePassword(userId, newPassword);
        return ResponseEntity.ok("Password changed!");
    }
}
