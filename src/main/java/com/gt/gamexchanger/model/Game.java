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
    // @Enumerated(value = EnumType.String)
    private GameStatus gameStatus;
 //   @Lob
//    private Byte[] image;
    private Long ownerId;
    private Long actualUserId;
    // @Enumerated(value = EnumType.String)
    private Visibility visibility;
    // @ManyToOne
    // private Shelf shelf;
//


}
