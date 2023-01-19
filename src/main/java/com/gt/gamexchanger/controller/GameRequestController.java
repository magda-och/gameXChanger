package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.enums.RequestStatus;
import com.gt.gamexchanger.model.RequestGameDto;
import com.gt.gamexchanger.service.GameRequestService;
import com.gt.gamexchanger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/requestgame")
public class GameRequestController {
    private final GameRequestService gameRequestService;

    @Autowired
    public GameRequestController(GameRequestService gameRequestService) {
        this.gameRequestService = gameRequestService;
    }
    @GetMapping("/requests")
    public List<RequestGameDto> getReqests() {
        return  gameRequestService.getAllRequest();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/addRequest")
    public RequestGameDto addReqestGame(@RequestBody RequestGameDto requestGameDto) {
        //requestGameDto.setRequestStatus(RequestStatus.WAITING);
        return gameRequestService.addGameRequest(requestGameDto);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deleteRequest/{requestId}")
    public boolean deleteRequest(@PathVariable("requestId") Long requestId) {
        return gameRequestService.removeGameRequestById(requestId);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/updateRequest/{requestId}")
    public void updateRequest(
            @PathVariable("requestId") Long requestId,
            @RequestBody RequestGameDto requestGameDto) {
        //requestGameDto.setRequestStatus(RequestStatus.ACCEPTED);
        gameRequestService.updateRequest(requestId, requestGameDto);
    }


    @GetMapping("/sendReqests/{userId}")
    public List<RequestGameDto> getSendReqests( @PathVariable("userId") Long userId) {
        return  gameRequestService.getMySendGameRequest(userId);
    }
    @GetMapping("/receivedReqests/{userId}")
    public List<RequestGameDto> getReceivedReqests( @PathVariable("userId") Long userId) {
        return  gameRequestService.getReceivedGameRequest(userId);
    }
//dodac pole message
    //zmienic statusy - add i post(update)
    //shelf service i controller
    //enumy
    //usunac toUserId i zaczytac z game

}
