package com.gt.gamexchanger.mapper;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.model.Game;
import org.springframework.stereotype.Component;

@Component
public class GameDtoMapper implements DtoMapper<GameDto, Game> {
    @Override
    public GameDto toDto(Game game) {

        if (game == null) {
            return null;
        }
        Long id = game.getId();
        return new GameDto(id,  game.getName(), game.getDescription(), game.getGameStatus(),
               game.getOwnerId(), game.getActualUserId(), game.getVisibility());
    }

    @Override
    public Game toDomainObject(GameDto gameDto) {
        Game game = new Game();
        if (gameDto.getId() != null) {
            game.setId(gameDto.getId());
        }
        game.setName(gameDto.getName());
        game.setDescription(gameDto.getDescription());
     game.setGameStatus(gameDto.getGameStatus());
    //   game.setGamePhoto(gameDto.getGamePhoto());
        game.setOwnerId(gameDto.getOwnerId());
        game.setActualUserId(gameDto.getActualUserId());
        game.setVisibility(gameDto.getVisibility());
        return game;
    }
}
