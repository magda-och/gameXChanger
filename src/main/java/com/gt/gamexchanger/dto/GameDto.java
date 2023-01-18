package com.gt.gamexchanger.dto;

import com.gt.gamexchanger.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {

    private Long id;
    private String name;
    private String description;
    private GameStatus gameStatus;
  //  private File gamePhoto;
 //   private Long ownerId;
 //   private Long actualUserId;



}
