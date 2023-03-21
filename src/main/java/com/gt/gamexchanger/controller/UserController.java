package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.exception.NoExistingUser;
import com.gt.gamexchanger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }


    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/name")
    public @ResponseBody List<UserDto> findUserByName(@RequestParam(value = "firstName", required = false) String firstName,
                                                      @RequestParam(value = "lastName", required = false) String lastName) {
        return userService.searchUsers(firstName, lastName);
        // TODO podzielic na kilka endpointow - /name, /lastname // searchingengine
        // ew konkatenacja imienia i nazwiska
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}")
    @Secured("ADMIN")
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

    @GetMapping("/friends/{userId}") //id/friends
    public ResponseEntity<List<UserDto>> getFriends(@PathVariable("userId") Long userId) {
        List<UserDto> myFriends = userService.getMyFriends(userId);
        return new ResponseEntity<>(myFriends, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId) {
        Optional<UserDto> user = userService.getUserById(userId);
        return new ResponseEntity<>(user.orElseThrow(), HttpStatus.OK);
    }

    @DeleteMapping("friends/{userId}/{friendId}")
    public ResponseEntity<?> removeFriend(@PathVariable Long friendId,
                                          @PathVariable Long userId) {
        try {
            userService.deleteFriend(userId, friendId);
            return ResponseEntity.noContent().build();
        } catch (NoExistingUser e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/me/{userEmail}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String userEmail) {
        Optional<UserDto> user = userService.findUserByEmail(userEmail);
        return new ResponseEntity<>(user.orElseThrow(), HttpStatus.OK);
    }

    @GetMapping("/notfriends/{userId}")
    public ResponseEntity<List<UserDto>> getNotMyFriends(@PathVariable("userId") Long userId) {
        List<UserDto> notFriends = userService.getUsersWhoAreNotMyFriends(userId);
        return new ResponseEntity<>(notFriends, HttpStatus.OK);
    }
}
