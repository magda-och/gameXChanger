package com.gt.gamexchanger.model;

import com.gt.gamexchanger.enums.GameStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@NoArgsConstructor
public class Game {


    private AtomicInteger atomicInteger;
    private Long id;
    private String name;
    private String description;
    private GameStatus gameStatus;
    private File gamePhoto;
    private Long ownerId;
    private Long actualUserId;


    public Game(String name, Long owner) {
        this.id = atomicInteger.longValue();
        this.name = name;
        this.ownerId = owner;
        this.actualUserId = this.id;
    }

}
