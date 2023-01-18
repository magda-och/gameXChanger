package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.model.UserDto;
import com.gt.gamexchanger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    //todo usuwanie by id 
    // to do add user- only with unique email
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

    @GetMapping("/{name}{lastname}")
    public List<UserDto> findUserByFullName(@RequestParam String name, @RequestParam String lastname){
        return userService.findUserByName(name, lastname);

    }

    @GetMapping("/{lastname}")
    public List<UserDto> findUserByLastName(@RequestParam String lastname){
        return userService.findUserByName(lastname);
    }





}
