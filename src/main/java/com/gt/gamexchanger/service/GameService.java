package com.gt.gamexchanger.service;

import com.gt.gamexchanger.mapper.GameDtoMapper;
import com.gt.gamexchanger.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final GameDtoMapper gameDtoMapper;


    public GameService(GameRepository gameRepository, GameDtoMapper gameDtoMapper) {
        this.gameRepository = gameRepository;
        this.gameDtoMapper = gameDtoMapper;
    }


}
