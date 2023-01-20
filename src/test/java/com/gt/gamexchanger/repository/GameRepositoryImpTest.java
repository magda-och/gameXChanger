package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.enums.GameStatus;
import com.gt.gamexchanger.enums.Visibility;
import com.gt.gamexchanger.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameRepositoryImpTest {
    GameRepositoryImp gameRepositoryImp = new GameRepositoryImp();

    @BeforeEach
    void setUp() {

        Game game1 = new Game();
        game1.setId(1L);
        game1.setName("Splendor");
        game1.setDescription("super Gra");
        game1.setOwnerId(1L);
        game1.setActualUserId(1L);
        game1.setGameStatus(GameStatus.AVAILABLE);
        game1.setVisibility(Visibility.PUBLIC);


    }

    @Test
    void addGameSuccessful() {
        //when
        gameRepositoryImp.addGame(createGame());
        // then
        assertEquals(gameRepositoryImp.getGames().get(1L), createGame());
    }

    @Test
    void getGamesByNameSuccessful() {
       gameRepositoryImp.addGame(createGame());
       gameRepositoryImp.addGame(createGame());
      List<Game> games = gameRepositoryImp.getGamesByName("Gra o tron");
        assertEquals(2, games.size());
    }


    @Test
    void getAllGamesSuccessful() {
        gameRepositoryImp.addGame(createGame());
        gameRepositoryImp.addGame(createGame());
        gameRepositoryImp.addGame(createGame());
        List<Game> games = gameRepositoryImp.getAllGames();
        assertEquals(3, games.size());
    }

    @Test
    void getGameByIdSuccessful(){
        gameRepositoryImp.addGame(createGame());
        gameRepositoryImp.addGame(createGame());
        Long idToTest = 1L;
        Game game = gameRepositoryImp.getGameById(idToTest);
        assertEquals(1, game.getId());

    }

    @Test
    void updateGame() {
    }

    @Test
    void deleteGame() {
        gameRepositoryImp.addGame(createGame());
        Game game =gameRepositoryImp.getGames().get(1L);
        gameRepositoryImp.deleteGame(game.getId());
        assertEquals(0, gameRepositoryImp.getGames().size());
    }

    @Test
    void getMyGamesSuccessful() {
        gameRepositoryImp.addGame(createGame());
        gameRepositoryImp.addGame(createGame());
        gameRepositoryImp.addGame(createGame());
        List<Game> games = gameRepositoryImp.getMyGames(1L);
        assertEquals(3, games.size());
    }

    @Test
    void getMyBorrowedGames() {
        gameRepositoryImp.addGame(createGame());
        gameRepositoryImp.addGame(createGame2());
        gameRepositoryImp.addGame(createGame3());
        List<Game> games = gameRepositoryImp.getMyGames(1L);
        assertEquals(2, games.size());
    }

    private static Game createGame() {
        Game game2 = new Game();
        game2.setId(1L);
        game2.setName("Gra o tron");
        game2.setDescription("graaaa aaaaaaa");
        game2.setOwnerId(1L);
        game2.setActualUserId(2L);
        game2.setGameStatus(GameStatus.AVAILABLE);
        game2.setVisibility(Visibility.PUBLIC);
        return game2;
    }
    private static Game createGame2() {
        Game game2 = new Game();
        game2.setId(2L);
        game2.setName("Gra o tron");
        game2.setDescription("graaaa aaaaaaa");
        game2.setOwnerId(2L);
        game2.setActualUserId(2L);
        game2.setGameStatus(GameStatus.AVAILABLE);
        game2.setVisibility(Visibility.PUBLIC);
        return game2;
    }
    private static Game createGame3() {
        Game game2 = new Game();
        game2.setId(2L);
        game2.setName("Gra o tron");
        game2.setDescription("graaaa aaaaaaa");
        game2.setOwnerId(1L);
        game2.setActualUserId(2L);
        game2.setGameStatus(GameStatus.AVAILABLE);
        game2.setVisibility(Visibility.PUBLIC);
        return game2;
    }

}