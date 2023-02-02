package com.gt.gamexchanger.mapper;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.enums.GameStatus;
import com.gt.gamexchanger.model.Game;
import com.gt.gamexchanger.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameDtoMapperTest {

    Game game;
    GameDto gameDto;
    User user;
    UserDto userDto;
    GameDtoMapper gameDtoMapper;

    @BeforeEach
    void setUp() {
        gameDtoMapper = new GameDtoMapper(new UserDtoMapper());
        user = new User();
        user.setId(1L);
        user.setFirstName("kasia");
        user.setLastName("Ks");
        user.setEmail("a@o.pl");
        user.setPassword("haslo");

        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setFirstName("kasia");
        userDto.setLastName("Ks");
        userDto.setEmail("a@o.pl");
        userDto.setPassword("haslo");

        game = new Game();
        game.setId(1L);
        game.setName("gra o tron");
        game.setGameStatus(GameStatus.AVAILABLE);
        game.setOwner(user);
        game.setActualUser(user);

        gameDto = new GameDto();
        gameDto.setName("splendor");
        gameDto.setId(2L);
        gameDto.setGameStatus(GameStatus.LENT);
        gameDto.setActualUserDto(userDto);
        gameDto.setOwnerDto(userDto);
    }

    @Test
    void toDto() {
    GameDto gamedto = gameDtoMapper.toDto(game);
       GameDto gameDtoExpected = new GameDto();
        gameDtoExpected.setId(1L);
        gameDtoExpected.setName("gra o tron");
        gameDtoExpected.setGameStatus(GameStatus.AVAILABLE);
        gameDtoExpected.setOwnerDto(userDto);
        gameDtoExpected.setActualUserDto(userDto);
        assertEquals( gameDtoExpected.getId(), gamedto.getId());
        assertEquals( gameDtoExpected.getOwnerDto(), gamedto.getOwnerDto());
        assertEquals( gameDtoExpected.getActualUserDto(), gamedto.getActualUserDto());
        assertEquals( gameDtoExpected.getGameStatus(), gamedto.getGameStatus());
        assertEquals( gameDtoExpected.getName(), gamedto.getName());


    }

    @Test
    void toDomainObject() {
      Game gameFromDto= gameDtoMapper.toDomainObject(gameDto);
      Game expectedGame = new Game();
        expectedGame.setName("splendor");
        expectedGame.setId(2L);
        expectedGame.setGameStatus(GameStatus.LENT);
        expectedGame.setOwner(user);
        expectedGame.setActualUser(user);
        assertEquals( expectedGame.getId(), gameFromDto.getId());
      // assertEquals( expectedGame.getOwner(), gameFromDto.getOwner());
       // assertEquals( expectedGame.getActualUser(), gameFromDto.getActualUser());
        assertEquals( expectedGame.getGameStatus(), gameFromDto.getGameStatus());
        assertEquals( expectedGame.getName(), gameFromDto.getName());
    }
}