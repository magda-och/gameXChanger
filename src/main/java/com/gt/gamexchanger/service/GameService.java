package com.gt.gamexchanger.service;

import com.gt.gamexchanger.dto.GameDto;
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
    private final GameDtoMapper gameDtoMapper;


    public GameService(GameRepository gameRepository, GameDtoMapper gameDtoMapper) {
        this.gameRepository = gameRepository;
        this.gameDtoMapper = gameDtoMapper;
    }

    public GameDto addGame(GameDto gameDto) {
        Game game = gameDtoMapper.toDomainObject(gameDto);
        gameRepository.addGame(game);
        return gameDtoMapper.toDto(game);
    }

    public List<GameDto> getAllGames() {
        return gameRepository.getAllGames().stream()
                .sorted(Comparator.comparing(Game::getName))
                .map(gameDtoMapper::toDto)
                .collect(Collectors.toList());
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
    public boolean deleteGame(Long id){
        if(getGameById(id).isPresent()){
          gameRepository.deleteGame(id);
          return true;
        }
        return false;
    }

}
