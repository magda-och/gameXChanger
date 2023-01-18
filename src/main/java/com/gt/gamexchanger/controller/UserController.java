package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    //todo usuwanie by id 
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
        userService.addUser(userDto);
        return "User's account successful created";
    }

    @PostMapping("/find")
    public List<UserDto> findUserByFullName(@RequestBody UserDto userDto){
        return userService.findUserByFullName(userDto.getName(), userDto.getLastName());

    }

    @PostMapping("/find/part")
    public List<UserDto> findUserByFirstOrLastName(@RequestBody String firstOrLastName){
        List<UserDto> userResults = userService.findUserByLastName(firstOrLastName);
        userResults.addAll(userService.findUserByFirstName(firstOrLastName));

        return userResults;
    }
}
