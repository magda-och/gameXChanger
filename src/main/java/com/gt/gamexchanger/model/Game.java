package com.gt.gamexchanger.model;

import com.gt.gamexchanger.enums.GameStatus;
import com.gt.gamexchanger.enums.Visibility;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
   @Enumerated(EnumType.STRING)
   @Column(name = "Game Status")
    private GameStatus gameStatus;
    //   @Lob
    //  private Byte[] image;
     @Enumerated(EnumType.STRING)
    private Visibility visibility;


    @ManyToOne
    @JoinColumn(name = "ownerId")
    private User owner;
    @ManyToOne
    private User actualUser;


}
