package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.model.ShelfDto;
import com.gt.gamexchanger.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ShelfController {
    private final ShelfService shelfService;

    @Autowired
    public ShelfController(ShelfService shelfService) {
        this.shelfService = shelfService;
    }
    @GetMapping("/shelf")
    public ShelfDto getShelf() {
        return shelfService.getShelf();
    }
}
