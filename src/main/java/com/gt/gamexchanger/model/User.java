package com.gt.gamexchanger.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    Set<Game> myGames;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actualUser")
    Set<Game> borrowedGames;

}
