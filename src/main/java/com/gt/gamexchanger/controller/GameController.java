package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addGame")
    public GameDto addGame(@RequestBody GameDto gameDto) {
        return gameService.addGame(gameDto);
    }
    @GetMapping("/gameById/{id}")
    public GameDto getGameById( @PathVariable Long id){
        return gameService.getGameById(id);
    }
    @GetMapping("/games")
    public List<GameDto> getAllGames(){
        return gameService.getAllGames();
    }

    @PostMapping("/gamesByName")
    public List<GameDto> getGamesByName(@RequestBody String name){
        return gameService.getGamesByName(name);
    }
}
