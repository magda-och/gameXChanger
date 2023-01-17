package com.gt.gamexchanger.model;

import com.gt.gamexchanger.enums.GameStatus;
import lombok.Getter;

import java.io.File;
@Getter
public class Game {

    private Long id;
    private String name;
    private String description;
    private GameStatus gameStatus;
    private File gamePhoto;



}
