package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.FriendDto;
import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {
    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping("/add")
    public String addNewFriend(@RequestBody FriendDto friendDto){
        friendService.saveFriend(friendDto);
        return "New friend are successfully created";
    }

    @GetMapping("/get/{id}")
    public List<UserDto> getAllFriends(@PathVariable Long id){
        return friendService.getFriends(id);
    }
}
