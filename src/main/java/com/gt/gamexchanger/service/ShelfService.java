package com.gt.gamexchanger.service;

import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.Shelf;
import com.gt.gamexchanger.model.ShelfDto;
import com.gt.gamexchanger.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class ShelfService {
    private final GameRepository gameRepository;
    private final DtoMapper<ShelfDto, Shelf> dtoMapper;

    public ShelfService(GameRepository gameRepository, DtoMapper<ShelfDto, Shelf> dtoMapper) {
        this.gameRepository = gameRepository;
        this.dtoMapper = dtoMapper;
    }
    //getAllmyGames-by my id
    //getAllborrowedGame- by actual owner id
    //

}
