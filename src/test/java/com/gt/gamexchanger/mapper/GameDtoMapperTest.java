//package com.gt.gamexchanger.mapper;
//
//import com.gt.gamexchanger.dto.GameDto;
//import com.gt.gamexchanger.enums.GameStatus;
//import com.gt.gamexchanger.model.Game;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class GameDtoMapperTest {
//
//    Game game;
//    GameDto expectedGame;
//    DtoMapper gameDtoMapper;
//
//    @BeforeEach
//    void setUp() {
//
//        game = new Game();
//        game.setId(1L);
//        game.setName("gra o tron");
//        game.setGameStatus(GameStatus.AVAILABLE);
//        game.setOwner(1L);
//        game.setActualUser(1L);
//
//        expectedGame = new GameDto();
//        expectedGame.setName("splendor");
//        expectedGame.setId(2L);
//        expectedGame.setGameStatus(GameStatus.LENT);
//        expectedGame.setActualUserId(1L);
//        expectedGame.setOwnerId(1L);
//    }
//
//    @Test
//    void toDto() {
//    GameDto gamedto = gameDtoMapper.toDto(game);
//       GameDto gameDtoExpected = new GameDto();
//        gameDtoExpected.setId(1L);
//        gameDtoExpected.setName("gra o tron");
//        gameDtoExpected.setGameStatus(GameStatus.AVAILABLE);
//        gameDtoExpected.setOwnerId(1L);
//        gameDtoExpected.setActualUserId(1L);
//        assertEquals( gameDtoExpected.getId(), gamedto.getId());
//        assertEquals( gameDtoExpected.getOwnerId(), gamedto.getOwnerId());
//        assertEquals( gameDtoExpected.getActualUserId(), gamedto.getActualUserId());
//        assertEquals( gameDtoExpected.getGameStatus(), gamedto.getGameStatus());
//        assertEquals( gameDtoExpected.getName(), gamedto.getName());
//
//
//    }
//
//    @Test
//    void toDomainObject() {
//      Game gameFromDto=   gameDtoMapper.toDomainObject(expectedGame);
//      Game expectedGame;
//        expectedGame = new Game();
//        expectedGame.setName("splendor");
//        expectedGame.setId(2L);
//        expectedGame.setGameStatus(GameStatus.LENT);
//        expectedGame.setActualUserId(1L);
//        expectedGame.setOwnerId(1L);
//        assertEquals( expectedGame.getId(), gameFromDto.getId());
//        assertEquals( expectedGame.getOwnerId(), gameFromDto.getOwnerId());
//        assertEquals( expectedGame.getActualUserId(), gameFromDto.getActualUserId());
//        assertEquals( expectedGame.getGameStatus(), gameFromDto.getGameStatus());
//        assertEquals( expectedGame.getName(), gameFromDto.getName());
//
//    }
//}