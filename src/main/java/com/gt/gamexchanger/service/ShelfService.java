package com.gt.gamexchanger.service;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.*;
import com.gt.gamexchanger.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelfService {
    private final GameRepository gameRepository;
    private final DtoMapper<GameDto, Game> dtoMapper;

    public ShelfService(GameRepository gameRepository, DtoMapper<GameDto, Game> dtoMapper) {
        this.gameRepository = gameRepository;
        this.dtoMapper = dtoMapper;
    }
    public List<GameDto> getAllMyGames(Long userId) {
        return gameRepository.getMyGames(userId).stream()
                .map(dtoMapper::toDto)
                .toList();
    }
    public List<GameDto> getAllBorowedGame(Long userId) {
        return gameRepository.getMyBorrowedGames(userId).stream()
                .map(dtoMapper::toDto)
                .toList();
    }

}
