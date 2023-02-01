package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.RequestFriendDto;
import com.gt.gamexchanger.enums.RequestStatus;
import com.gt.gamexchanger.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class FriendRequestController {
    private final FriendRequestService friendRequestService;

    @Autowired
    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }
    @GetMapping
    public List<RequestFriendDto> getAllRequests() {
        return  friendRequestService.getAllRequest();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/")
    public RequestFriendDto addReqest(@RequestBody RequestFriendDto requestGameDto) {
        //requestGameDto.setRequestStatus(RequestStatus.WAITING);
        return friendRequestService.addFriendRequest(requestGameDto);
    }
   /* @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{requestId}")
    public boolean deleteRequest(@PathVariable("requestId") Long requestId) {
        return friendRequestService.removeFriendRequestById(requestId);
    }*/
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{requestId}")
    public void updateStatus(
            @PathVariable("requestId") Long requestId,
            @RequestBody RequestStatus requestStatus) {
        //requestGameDto.setRequestStatus(RequestStatus.ACCEPTED);
        friendRequestService.updateStatus(requestId, requestStatus);
    }

    /*@GetMapping("/send/{userId}")
    public List<RequestFriendDto> getSendReqests(@PathVariable("userId") Long userId) {
        return  friendRequestService.getMySendFriendRequest(userId);
    }*/
   /* @GetMapping("/received/{userId}")
    public List<RequestFriendDto> getReceivedReqests(@PathVariable("userId") Long userId) {
        return  friendRequestService.getReceivedFriendRequest(userId);
    }*/
}
