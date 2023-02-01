package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.model.Game;
import com.gt.gamexchanger.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

   @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{ownerId}")
    public GameDto addGame(@PathVariable("ownerId") Long ownerId,
                           @RequestBody GameDto gameDto) {
        return gameService.addGame(ownerId, gameDto);
    }

    @GetMapping("/{id}")
    public Optional<Game> getGameById(@PathVariable Long id) {
        return gameService.getGameById(id);
    }

 @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<GameDto> getAllGames() {
        return gameService.getAllGames();
    }

    // search names, wprowadzić kryteria wyszukiwania
    //    //search games
    //    // nazwy endpointów, które są zachowaniami
    //    // zmien na @RequestParam
    //    // lub nowu model, który zawiera kryteria wyszukiwania SearchCriteriaRequest
    //    // search kontroller zrobić i tam wyszukiwania userów i gameów
    @GetMapping("/searchGame")
    public List<GameDto> getGamesByName(@RequestParam(value = "name") String name) {
        return gameService.getGamesByName(name);
    }

    @GetMapping("/searchName")
    public List<GameDto> getGamesByContainingName(@RequestParam(value = "name") String name) {
        return gameService.getByContainingName(name);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{gameId}")
    public String deleteGame(@PathVariable Long gameId) {
        gameService.deleteGame(gameId);
        return "Game has already deleted";
    }

    //       update game
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{gameId}")
    public void updateGame(@PathVariable(
            "gameId") Long gameId,
                           @RequestBody GameDto gameDto) {
        gameService.updateGame(gameId, gameDto);
    }
    @GetMapping("/myGames/{userId}")
    public List<GameDto> getMyGames(@PathVariable("userId") Long userId) {
        return  gameService.getAllMyGames(userId);
    }
    @GetMapping("/borrowedGames/{userId}")
    public List<GameDto> getBorrowedGames(@PathVariable("userId") Long userId) {
        return  gameService.getAllBorrowedGame(userId);
    }

    @PatchMapping("/borrow/{gameId}")
    public void borrowGame(@PathVariable("gameId") Long gameId,
                           @RequestBody String email){
        gameService.borrowGame(gameId, email);
    }
}
