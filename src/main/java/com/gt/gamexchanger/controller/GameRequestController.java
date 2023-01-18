package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.service.GameRequestService;
import com.gt.gamexchanger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    //funkcje mappujące
}
