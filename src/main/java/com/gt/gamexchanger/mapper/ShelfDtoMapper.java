package com.gt.gamexchanger.mapper;

import com.gt.gamexchanger.model.RequestGame;
import com.gt.gamexchanger.model.RequestGameDto;
import com.gt.gamexchanger.model.Shelf;
import com.gt.gamexchanger.model.ShelfDto;
import org.springframework.stereotype.Component;

@Component
public class ShelfDtoMapper implements DtoMapper<ShelfDto, Shelf>{
    @Override
    public ShelfDto toDto(Shelf shelf) {
        if (shelf == null) {
            return null;
        }
        ShelfDto shelfDto =  new ShelfDto(shelf.getOwnerId());
        shelfDto.setShelfId(shelf.getShelfId());
        return shelfDto;
    }

    @Override
    public Shelf toDomainObject(ShelfDto shelfDto) {

        Shelf shelf = new Shelf();
        shelf.setShelfId(shelf.getShelfId());
        shelf.setOwnerId(shelf.getOwnerId());
        return shelf;
    }

}
