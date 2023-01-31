package com.gt.gamexchanger.service;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.mapper.GameDtoMapper;
import com.gt.gamexchanger.model.Game;
import com.gt.gamexchanger.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final DtoMapper<GameDto, Game> gameDtoMapper;



    public GameService(GameRepository gameRepository, GameDtoMapper gameDtoMapper) {
        this.gameRepository = gameRepository;
        this.gameDtoMapper = gameDtoMapper;
    }

    public GameDto addGame(GameDto gameDto) {
        Game game = (Game) gameDtoMapper.toDomainObject(gameDto);
        gameRepository.addGame(game);
        return (GameDto) gameDtoMapper.toDto(game);
    }

    public List<GameDto> getAllGames() {

   List<Game> games = gameRepository.getAllGames().stream()
           .sorted(Comparator.comparing(Game::getName)).toList();

   return games.stream().map(gameDtoMapper::toDto).collect(Collectors.toList());
    }

    public List<GameDto> getGamesByName(String name) {
        return gameRepository.getGamesByName(name).stream()
                .sorted(Comparator.comparing(Game::getGameStatus))
                .map(gameDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<Game> getGameById(Long id) {
        return Optional.ofNullable(gameRepository.getGameById(id));
    }

    public boolean deleteGame(Long id) {
        if (getGameById(id).isPresent()) {
            gameRepository.deleteGame(id);
            return true;
        }
        return false;
    }

    public void updateGame(Long gameId, GameDto gameDto) {
       Optional<Game> gameToUpdate = gameRepository.findById(gameId);
        if (gameToUpdate.isPresent()) {
            var game = gameToUpdate.get();
            updateGameFields(game, gameDto);
        }
    }

    private Game updateGameFields(Game game, GameDto gameDto) {
        if (gameDto.getName() != null) {
            game.setName(gameDto.getName());
        }
        if (gameDto.getDescription() != null) {
            game.setDescription(gameDto.getDescription());
        }
        if (gameDto.getGameStatus() != null) {
            game.setGameStatus(gameDto.getGameStatus());
        }
        if (gameDto.getActualUserDto() != null) {
            game.setActualUser(game.getActualUser());
        }
        if (gameDto.getVisibility() != null) {
            game.setVisibility(gameDto.getVisibility());
        }
        return game;
    }

    public List<GameDto> getAllMyGames(Long userId) {
      return    gameRepository.findAllByOwnerId(userId).stream()
                .map(gameDtoMapper::toDto)
                .toList();

    }
    public List<GameDto> getAllBorowedGame(Long userId) {
        return gameRepository.getMyBorrowedGames(userId).stream()
                .map(gameDtoMapper::toDto)
                .toList();
    }
}
