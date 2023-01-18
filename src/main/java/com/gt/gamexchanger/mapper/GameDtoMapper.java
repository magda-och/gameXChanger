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
        return new GameDto(game.getId(), game.getName(), game.getDescription(), game.getGameStatus(), game.getGamePhoto());
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
        game.setGamePhoto(gameDto.getGamePhoto());
        return game;
    }
}
