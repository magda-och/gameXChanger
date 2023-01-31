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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
   @Enumerated(value = EnumType.STRING)
    private GameStatus gameStatus;
    //   @Lob
    //  private Byte[] image;
    @ManyToOne
    @JoinColumn(name = "ownerId")
    private User owner;
    @ManyToOne
    private User actualUser;

     @Enumerated(value = EnumType.STRING)
    private Visibility visibility;




}
