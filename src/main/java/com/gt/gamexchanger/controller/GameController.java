package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.model.Game;
import com.gt.gamexchanger.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public GameDto addGame(@RequestBody GameDto gameDto) {
        return gameService.addGame(gameDto);
    }
    @GetMapping("/{id}")
    public Optional<Game> getGameById(@PathVariable Long id){
        return gameService.getGameById(id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping()
    public List<GameDto> getAllGames(){
        return gameService.getAllGames();
    }

    // search names, wprowadzić kryteria wyszukiwania
    //search games
    // nazwy endpointów, które są zachowaniami
    // zmien na @RequestParam
    // lub nowu model, który zawiera kryteria wyszukiwania SearchCriteriaRequest
    // search kontroller zrobić i tam wyszukiwania userów i gameów
    @PostMapping("/searchGame")
    public List<GameDto> getGamesByName(@RequestParam String name){
        return gameService.getGamesByName(name);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{gameId}")
    public String deleteGame(@PathVariable Long gameId){
        gameService.deleteGame(gameId);
        return "Game has already deleted";
    }

     //       update game
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{gameId}")
public void updateGame(@PathVariable(
        "gameId") Long gameId,
                       @RequestBody GameDto gameDto){
        gameService.updateGame(gameId, gameDto);
}
}
