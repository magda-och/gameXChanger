package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.model.UserDto;
import com.gt.gamexchanger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    //todo find user by id and find user by name
    private final UserService userService;

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
        userService.addUser(userDto);
        return "User's account successful created";
    }


}
