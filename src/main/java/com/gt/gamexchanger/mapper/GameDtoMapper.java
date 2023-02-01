package com.gt.gamexchanger.mapper;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.model.Game;
import com.gt.gamexchanger.model.User;
import org.springframework.stereotype.Component;

@Component
public class GameDtoMapper implements DtoMapper<GameDto, Game> {

    private DtoMapper<UserDto, User> mapperForUser;

    public GameDtoMapper(DtoMapper<UserDto, User> mapperForUser) {
        this.mapperForUser = mapperForUser;
    }

    @Override
    public GameDto toDto(Game game) {
        if (game == null) {
            return null;
        }
        Long id = game.getId();
        UserDto ownerDto = mapperForUser.toDto(game.getOwner());
        UserDto actualDto = mapperForUser.toDto(game.getActualUser());
        return new GameDto(id,  game.getName(), game.getDescription(), game.getGameStatus(),
             ownerDto, actualDto, game.getVisibility());
    }

    @Override
    public Game toDomainObject(GameDto gameDto) {
//       User owner = mapperForUser.toDomainObject(gameDto.getOwnerDto());
//        User actual = mapperForUser.toDomainObject(gameDto.getOwnerDto());

        Game game = new Game();
        if (gameDto.getId() != null) {
            game.setId(gameDto.getId());
        }
        game.setName(gameDto.getName());
        game.setDescription(gameDto.getDescription());
     game.setGameStatus(gameDto.getGameStatus());
    //   game.setGamePhoto(gameDto.getGamePhoto());
//        game.setOwner(owner);
//        game.setActualUser(actual);
        game.setVisibility(gameDto.getVisibility());
        return game;
    }
}
