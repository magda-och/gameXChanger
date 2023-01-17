package com.gt.gamexchanger.service;

import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.Shelf;
import com.gt.gamexchanger.model.ShelfDto;
import com.gt.gamexchanger.repository.ShelfRepository;
import org.springframework.stereotype.Service;

@Service
public class ShelfService {
    private final ShelfRepository shelfRepository;
    private final DtoMapper<ShelfDto, Shelf> dtoMapper;

    public ShelfService(ShelfRepository shelfRepository, DtoMapper<ShelfDto, Shelf> dtoMapper) {
        this.shelfRepository = shelfRepository;
        this.dtoMapper = dtoMapper;
    }
}
