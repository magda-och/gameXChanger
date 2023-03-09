package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.enums.GameStatus;
import com.gt.gamexchanger.model.Game;
import com.gt.gamexchanger.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;


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
        return gameService.getGamesByName(name); // dodać {/name}
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{gameId}")
    public String deleteGame(@PathVariable Long gameId) {
        gameService.deleteGame(gameId);
        return "Game has already deleted";
    }

    @GetMapping("/searchName")
    public List<GameDto> getGamesByContainingName(@RequestParam(value = "name") String name) {
        return gameService.getByContainingName(name);
    }

    //       update game
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{gameId}")
    public void updateGame(@PathVariable(
            "gameId") Long gameId,
                           @RequestParam("gameStatus") GameStatus gameStatus,
                           @RequestParam("ownerId") Long ownerId) {
        System.out.println("cos");
        gameService.updateGame(gameId, gameStatus,ownerId);
    }

    @GetMapping("/myGames/{userId}")
    public List<GameDto> getMyGames(@PathVariable("userId") Long userId) {
        return gameService.getAllMyGames(userId);
    }

    @GetMapping("/borrowedGames/{userId}")
    public List<GameDto> getBorrowedGames(@PathVariable("userId") Long userId) {
        return gameService.getAllBorrowedGame(userId);
    }

    @PatchMapping("/borrowGame/{gameId}/{userId}")
    public void borrowGame(@PathVariable("gameId") Long gameId,
                           @PathVariable Long userId) {
        gameService.borrowGame(gameId, userId);
    }
    @PatchMapping("/giveBackGame/{gameId}/{userId}")
    public void giveBackGame(@PathVariable("gameId") Long gameId,
                             @PathVariable("userId") Long userId) {

        gameService.giveBackGame(gameId, userId);
    }

    @GetMapping("/myfriends/{userId}")
    public List<Game> getMyFriendsGames(@PathVariable("userId") Long userId) {
        return gameService.getMyFriendsGames(userId);
    }
}
