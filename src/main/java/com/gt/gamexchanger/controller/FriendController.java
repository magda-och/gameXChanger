package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.UserDto;

import com.gt.gamexchanger.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<?> addNewFriend(@RequestBody UserDto currentUserDto, @RequestParam("friendId")Long friendId) {
        friendService.saveFriend(currentUserDto, friendId);
         return ResponseEntity.ok("Friend added successfully");
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getFriends(@RequestParam Long currentUserId) {
        List<UserDto> myFriends = friendService.getFriends(currentUserId);
        return new ResponseEntity<>(myFriends, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> remove(@PathVariable Long userId, @RequestParam Long currentUserId) {
        try{
            friendService.deleteFriends(userId, currentUserId);
            return ResponseEntity.noContent().build();
        }
        catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }
}
