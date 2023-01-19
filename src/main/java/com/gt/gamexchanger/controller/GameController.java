package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.model.Game;
import com.gt.gamexchanger.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<Game> getGameById(@PathVariable Long id){
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
    @DeleteMapping("/{gameId}")
    public String deleteGame(@PathVariable Long gameId){
        gameService.deleteGame(gameId);
        return "Geme has already deleted";
    }

     //       update game

}
