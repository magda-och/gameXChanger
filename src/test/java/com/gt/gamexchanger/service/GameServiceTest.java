package com.gt.gamexchanger.service;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.enums.GameStatus;
import com.gt.gamexchanger.enums.Visibility;
import com.gt.gamexchanger.exception.NoGameExists;
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

import java.util.ArrayList;
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
        assertEquals(3, list.size());
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
        assertEquals(2, serviceUnderTest.getAllGames().size());
    }

    @Test
    void deleteGameException() {
        createGames();
        NoGameExists thrown = assertThrows(
                NoGameExists.class,
                () -> serviceUnderTest.deleteGame(6L)
        );

        assertEquals("No Game exist", thrown.getMessage());

    }
    @Test
    void getMyGamesTest(){
        createGames();
       List<GameDto> result = serviceUnderTest.getAllMyGames(1L);
        assertEquals(2, result.size());
    }

    @Test
    void getBorrowedGames(){
        createGames();
        List<GameDto> result = serviceUnderTest.getAllBorrowedGame(2L);
        assertEquals(1, result.size());
    }
    @Test
    void findByContainingName(){
        createGames();
        List<GameDto> result = serviceUnderTest.getByContainingName("Gra");
        assertEquals(2, result.size());
    }
//    @Test
//    void addGame(){
//        createGames();
//        GameDto game1 = new GameDto();
//        game1.setId(1L);
//        game1.setName("Gra o tron");
//        game1.setDescription("graaaa aaaaaaa");
//        game1.setGameStatus(GameStatus.AVAILABLE);
//        game1.setVisibility(Visibility.PUBLIC);
//
//        serviceUnderTest.addGame(1L, game1);
//        List<Game> result = gameRepository.getAllGames();
//
//        assertEquals(3, result.size());
//    }

    @Test
    void updateGame(){
        createGames();
        GameDto game1 = new GameDto();
        game1.setId(1L);
        game1.setName("updateowana");
        game1.setDescription("up up up");
        game1.setGameStatus(GameStatus.LENT);
        game1.setVisibility(Visibility.PRIVATE);
        serviceUnderTest.updateGame(1L, game1);

        Game gameDto = gameRepository.getGameById(1L);

        assertEquals("updateowana", gameRepository.getGames().get(1L).getName());
        assertEquals("up up up", gameRepository.getGames().get(1L).getDescription());
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

        Game game3 = new Game();
        game3.setId(2L);
        game3.setName("Gra jakaś inna");
        game3.setDescription("graaaa aaaaaaa");
        game3.setOwner(user1);
        game3.setActualUser(user2);
        game3.setGameStatus(GameStatus.AVAILABLE);
        game3.setVisibility(Visibility.PUBLIC);
        gameRepository.save(game3);
        List<Game> user1Games = new ArrayList<>();
        user1Games.add(game1);
        user1Games.add(game3);
        user1.setMyGames(user1Games);

        List<Game> user2Games = new ArrayList<>();
        user2Games.add(game2);
        user2.setMyGames(user2Games);
    }
}

class ServiceUnderTest extends GameService {
    public ServiceUnderTest(GameRepository gameRepository, GameDtoMapper gameDtoMapper, UserRepository userRepository) {
        super( gameRepository, gameDtoMapper, userRepository);
    }
}
