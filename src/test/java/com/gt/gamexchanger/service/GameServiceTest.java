package com.gt.gamexchanger.service;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.enums.GameStatus;
import com.gt.gamexchanger.enums.Visibility;
import com.gt.gamexchanger.mapper.GameDtoMapper;
import com.gt.gamexchanger.model.Game;
import com.gt.gamexchanger.repository.GameRepository;
import com.gt.gamexchanger.repository.GameRepositoryImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class GameServiceTest {
    GameRepository gameRepository;
    ServiceUnderTest serviceUnderTest;
    GameDtoMapper mapper;

    @BeforeEach
    void setUp() {
        gameRepository = new GameRepositoryImp();
        mapper = new GameDtoMapper();
        serviceUnderTest = new ServiceUnderTest(gameRepository, mapper);
    }

    @Test
    void getAllGames() {
        createGames();
        List<GameDto> list =serviceUnderTest.getAllGames();
        assertEquals(2, list.size());
    }

    @Test
    void getGamesByName() {
    }

    @Test
    void getGameById() {
    }

    @Test
    void deleteGame() {
    }

    private void createGames() {
        Game game1 = new Game();
        game1.setId(2L);
        game1.setName("Gra o tron");
        game1.setDescription("graaaa aaaaaaa");
        game1.setOwnerId(2L);
        game1.setActualUserId(2L);
        game1.setGameStatus(GameStatus.AVAILABLE);
        game1.setVisibility(Visibility.PUBLIC);
        gameRepository.addGame(game1);

        Game game2 = new Game();
        game2.setId(2L);
        game2.setName("Gra o tron");
        game2.setDescription("graaaa aaaaaaa");
        game2.setOwnerId(2L);
        game2.setActualUserId(2L);
        game2.setGameStatus(GameStatus.AVAILABLE);
        game2.setVisibility(Visibility.PUBLIC);
        gameRepository.addGame(game2);
    }
}

class ServiceUnderTest extends GameService {
    public ServiceUnderTest(GameRepository gameRepository, GameDtoMapper gameDtoMapper) {
        super(gameRepository, gameDtoMapper);
    }
}
