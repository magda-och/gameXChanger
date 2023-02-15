package com.gt.gamexchanger.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String description;
   @Enumerated(EnumType.STRING)
   @Column(name = "Game Status")
    private GameStatus gameStatus;
    //   @Lob
    //  private Byte[] image;

     @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @JsonBackReference("myGames")
    @ManyToOne
    @JoinColumn(name = "ownerId")
    private User owner;
    @JsonBackReference("borrowedGames")
    @ManyToOne
    private User actualUser;


}
