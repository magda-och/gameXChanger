package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class GameControllerTest {

    GameService gameService ;
    UnderTest underTest;
    GameDto gameDto;
    UserDto userDto;

    @BeforeEach
    void setUp() {
        gameDto = new GameDto();
        gameService = mock(GameService.class);
        underTest = new UnderTest();
        userDto = new UserDto();
    }

    @Test
    void addGame() {
        underTest.addGame(userDto.getId(), gameDto);
        verify(gameService, times(1)).addGame(userDto.getId(), gameDto);
    }

    @Test
    void getGameById() {
        underTest.getGameById(1L);
        verify(gameService, times(1)).getGameById(1L);
    }

    @Test
    void getAllGames() {
        underTest.getAllGames();
        verify(gameService, times(1)).getAllGames();
    }

    @Test
    void getGamesByName() {
        underTest.getGamesByName("spendor");
        verify(gameService, times(1)).getGamesByName("spendor");
    }

    @Test
    void deleteGame() {
        underTest.deleteGame(gameDto.getId());
        verify(gameService, times(1)).deleteGame(gameDto.getId());
    }

    @Test
    void getGamesByContainingName(){
        underTest.getGamesByContainingName(gameDto.getName());
        verify(gameService, times(1)).getByContainingName(gameDto.getName());
    }
    @Test
    void updateGame(){
        underTest.updateGame(1L, gameDto);
        verify(gameService, times(1)).updateGame(1L, gameDto);
    }

    @Test
    void getMyGames(){
        underTest.getMyGames(1L);
        verify(gameService, times(1)).getAllMyGames(1L);
    }
    @Test
    void getBorrowedGames(){
        underTest.getBorrowedGames(1L);
        verify(gameService, times(1)).getAllBorrowedGame(1L);
    }
    @Test
    void borrowGame(){
        underTest.borrowGame(1L, "gameDto@o.pl");
        verify(gameService, times(1)).borrowGame(1L, "gameDto@o.pl");
    }

    class UnderTest extends GameController {
        public UnderTest() {
            super(gameService);
        }
    }
}