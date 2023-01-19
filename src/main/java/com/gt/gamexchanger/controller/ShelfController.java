package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/shelf")
public class ShelfController {
    private final ShelfService shelfService;

    @Autowired
    public ShelfController(ShelfService shelfService) {

        this.shelfService = shelfService;
    }
    @GetMapping("/getMyGames/{userId}")
    public List<GameDto> getMyGames(@PathVariable("userId") Long userId) {
        return  shelfService.getAllMyGames(userId);
    }
    @GetMapping("/getBorrowedGames/{userId}")
    public List<GameDto> getBorrowedGames(@PathVariable("userId") Long userId) {
        return  shelfService.getAllBorowedGame(userId);
    }

}
