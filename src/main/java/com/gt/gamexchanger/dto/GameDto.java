package com.gt.gamexchanger.dto;

import com.gt.gamexchanger.enums.GameStatus;
import com.gt.gamexchanger.enums.Visibility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {

   private Long id;
    private String name;
    private GameStatus gameStatus;
 // private byte[] image;
  private UserDto ownerDto;
  private UserDto actualUserDto;
   private Visibility visibility;



}
