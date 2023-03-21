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
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameServiceTest {
    @Mock
    private GameRepository gameRepository;
    @Mock
    private UserRepository userRepository;
    @Autowired
    GameService serviceUnderTest;
    @Mock
    private GameDtoMapper mapper;

    private User user1;

    private Game game1;

    private Game game2;

    @BeforeEach
    void setUp() {
        mapper = new GameDtoMapper(new UserDtoMapper());
        serviceUnderTest = new GameService(gameRepository, mapper, userRepository);
        createGames();

    }

    @Test
    void getAllGames_ShouldReturnProperly() {

        List<Game> allGames = new ArrayList<>();
        allGames.add(game1);
        allGames.add(game2);

        GameDto game1Dto = mapper.toDto(game1);
        GameDto game2Dto = mapper.toDto(game2);

        List<GameDto> allGamesDto = new ArrayList<>();
        allGamesDto.add(game1Dto);
        allGamesDto.add(game2Dto);

        when(gameRepository.findAll()).thenReturn(allGames);

        assertEquals(allGamesDto, serviceUnderTest.getAllGames());
    }

    @Test
    void getGamesByName_WhenGamesAdded_ShouldReturnProperly() {

        List<GameDto> list = List.of(mapper.toDto(game1));
        when(gameRepository.findAllByName("Gra o tron")).thenReturn(List.of(game1));
        assertEquals(list, serviceUnderTest.getGamesByName("Gra o tron"));
    }

    @Test
    void getGameById() {

        Optional<Game> actualGame =serviceUnderTest.getGameById(1L);
        assertEquals("Gra o tron", actualGame.get().getName());
    }

    @Test
    void deleteGame() {

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

       List<GameDto> result = serviceUnderTest.getAllMyGames(1L);
        assertEquals(2, result.size());
    }

    @Test
    void getBorrowedGames(){

        List<GameDto> result = serviceUnderTest.getAllBorrowedGame(2L);
        assertEquals(1, result.size());
    }
    @Test
    void findByContainingName(){

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

  /*  @Test
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
    }*/

    private void createGames() {
        user1 = new User();
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

        game1 = new Game();
        game1.setId(1L);
        game1.setName("Gra o tron");
        game1.setOwner(user1);
        game1.setActualUser(user1);
        game1.setGameStatus(GameStatus.AVAILABLE);
        game1.setVisibility(Visibility.PUBLIC);

        game2 = new Game();
        game2.setId(2L);
        game2.setName("splendor");
        game2.setOwner(user2);
        game2.setActualUser(user2);
        game2.setGameStatus(GameStatus.AVAILABLE);
        game2.setVisibility(Visibility.PUBLIC);

        Game game3 = new Game();
        game3.setId(2L);
        game3.setName("Gra inna");
        game3.setOwner(user1);
        game3.setActualUser(user2);
        game3.setGameStatus(GameStatus.AVAILABLE);
        game3.setVisibility(Visibility.PUBLIC);


        List<Game> user1Games = new ArrayList<>();
        user1Games.add(game1);
        user1Games.add(game3);
       // user1.setMyGames(user1Games);

        List<Game> user2Games = new ArrayList<>();
        user2Games.add(game2);
       // user2.setMyGames(user2Games);
    }
}

