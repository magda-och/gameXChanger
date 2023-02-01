package com.gt.gamexchanger.service;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.enums.GameStatus;
import com.gt.gamexchanger.enums.Visibility;
import com.gt.gamexchanger.mapper.GameDtoMapper;
import com.gt.gamexchanger.mapper.UserDtoMapper;
import com.gt.gamexchanger.model.Game;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.repository.GameRepository;
import com.gt.gamexchanger.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
@RunWith(MockitoJUnitRunner.class)
class GameServiceTest {

    GameRepositoryImp gameRepository;

    UserRepository userRepository;
    GameService serviceUnderTest;
    GameDtoMapper mapper;

    @BeforeEach
    void setUp() {
        gameRepository = new GameRepositoryImp();
        mapper = new GameDtoMapper(new UserDtoMapper());
        serviceUnderTest = new ServiceUnderTest(gameRepository, mapper, userRepository);
    }

    @Test
    void getAllGames() {
        createGames();
        List<GameDto> list = serviceUnderTest.getAllGames();
        assertEquals(2, list.size());
    }

    @Test
    void getGamesByName() {
        createGames();
        List<GameDto> list = serviceUnderTest.getGamesByName("Gra o tron");
        assertEquals(1, list.size());
    }

    @Test
    void getGameById() {
        createGames();
        Optional<Game> actualGame =serviceUnderTest.getGameById(1L);
        assertEquals("Gra o tron", actualGame.get().getName());
    }

    @Test
    void deleteGame() {
        createGames();
        serviceUnderTest.deleteGame(1L);
        assertEquals(1, serviceUnderTest.getAllGames().size());
    }

    private void createGames() {
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("kasia");
        user1.setLastName("Ks");
        user1.setEmail("a@o.pl");
        user1.setPassword("haslo");

        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("ula");
        user2.setLastName("sy");
        user2.setEmail("b@o.pl");
        user2.setPassword("password");

        Game game1 = new Game();
        game1.setId(1L);
        game1.setName("Gra o tron");
        game1.setDescription("graaaa aaaaaaa");
        game1.setOwner(user1);
        game1.setActualUser(user1);
        game1.setGameStatus(GameStatus.AVAILABLE);
        game1.setVisibility(Visibility.PUBLIC);
        gameRepository.save(game1);

        Game game2 = new Game();
        game2.setId(2L);
        game2.setName("splendor");
        game2.setDescription("graaaa aaaaaaa");
        game2.setOwner(user2);
        game2.setActualUser(user2);
        game2.setGameStatus(GameStatus.AVAILABLE);
        game2.setVisibility(Visibility.PUBLIC);
        gameRepository.save(game2);
    }
}

class ServiceUnderTest extends GameService {
    public ServiceUnderTest(GameRepository gameRepository, GameDtoMapper gameDtoMapper, UserRepository userRepository) {
        super( gameRepository, gameDtoMapper, userRepository);
    }
}
