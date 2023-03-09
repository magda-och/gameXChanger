package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.RequestFriendDto;
import com.gt.gamexchanger.enums.RequestStatus;
import com.gt.gamexchanger.service.FriendRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/friends/requests")
@RequiredArgsConstructor
public class FriendRequestController {

    private final FriendRequestService friendRequestService;

    @GetMapping
    public List<RequestFriendDto> getAllRequests() {
        return  friendRequestService.getAllRequest();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public RequestFriendDto addRequest(@RequestBody RequestFriendDto requestFriendDto) {
        return friendRequestService.addFriendRequest(requestFriendDto);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{requestId}")
    public boolean deleteRequest(@PathVariable("requestId") Long requestId) {
        return friendRequestService.removeFriendRequestById(requestId);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{requestId}")
    public void updateStatus(
            @PathVariable("requestId") Long requestId,
            @RequestParam("requestStatus") RequestStatus requestStatus) {
        friendRequestService.updateStatus(requestId, requestStatus);
    }

    @GetMapping("/send/{userId}")
    public List<RequestFriendDto> getSendRequests(@PathVariable("userId") Long userId) {
        return  friendRequestService.getMySendFriendRequest(userId);
    }
    @GetMapping("/received/{userId}")
    public List<RequestFriendDto> getReceivedRequests(@PathVariable("userId") Long userId) {
        return  friendRequestService.getReceivedFriendRequest(userId);
    }
}
