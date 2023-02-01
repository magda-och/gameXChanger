package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class GameControllerTest {
    GameService gameService ;
    UnderTest underTest;
    GameDto gameDto;
    @BeforeEach
    void setUp() {
        gameDto = new GameDto();
        gameService = mock(GameService.class);
        underTest = new UnderTest();
    }

//    @Test
//    void addGame() {
//        underTest.addGame( gameDto);
//        verify(gameService, times(1)).addGame( gameDto);
//
//    }

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

    }
    class UnderTest extends GameController{

        public UnderTest() {
            super(gameService);
        }
    }
}