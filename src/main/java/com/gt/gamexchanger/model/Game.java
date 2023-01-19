package com.gt.gamexchanger.model;

import com.gt.gamexchanger.enums.GameStatus;
import com.gt.gamexchanger.enums.Visibility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {


    private Long id;
    private String name;
    private String description;
    private GameStatus gameStatus;
 //   private File gamePhoto;
    private Long ownerId;
    private Long actualUserId;
    private Visibility visibility;
//



}
