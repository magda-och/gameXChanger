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

    // to do add user- only with unique email
    // todo update user
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }


//        @PostMapping("/find")
//    public  @ResponseBody List<UserDto> findUserByName(@RequestParam(value = "firstName", required = false) String firstName,
//                                        @RequestParam(value = "lastName", required = false) String lastName) {
//        return userService.findUser(firstName, lastName);
//    }
// tez requestParam ta metoda bedxzie get
    //wywalic find
    @PostMapping("/find")
    public List<UserDto> findUserByName(@RequestBody String name) {
        return userService.findUsersByName(name);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
//    @PatchMapping("/update/{userId}")
//    public String updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
//        if (userService.updateUser(userId, userDto)) {
//            return "User updated!";
//        }
//        return "User doesn't exist";
//    }
//
//    @PostMapping("/update/password/{userId}")
//    public String updatePassword(@PathVariable Long userId, @RequestBody String newPassword) {
//        if (userService.changePassword(userId, newPassword)) {
//            return "Password changed!";
//        }
//        return "User doesn't exist";
//    }
//}
