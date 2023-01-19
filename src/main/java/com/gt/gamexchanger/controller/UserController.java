package com.gt.gamexchanger.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
@RequestMapping("/user")
public class UserController {

    // to do add user- only with unique email
    // todo update user
    private final UserService  userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public String addNewUser(@RequestBody UserDto userDto){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(userDto);
            if (json.contains("null")){
                return "You didn't fill all fields correctly";
            }
            userService.addUser(userDto);
            return "User's account successful created";
        } catch (JsonProcessingException e) {
            return "Error";
        }
    }

    @PostMapping("/find")
    public List<UserDto> findUserByName(@RequestBody String name){
        return userService.findUserByName(name);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        if (userService.deleteUser(userId)) {
            return "User successfully removed!";
        }
        return "User doesn't exist";
    }
}
