package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void addGame() {
        underTest.addGame(gameDto);
        verify(gameService, times(1)).addGame(gameDto);

    }

    @Test
    void getGameById() {
        underTest.getGameById(1L);
        verify(gameService, times(1)).getGameById(1L);
    }

    @Test
    void getAllGames() {
    }

    @Test
    void getGamesByName() {
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