package com.gt.gamexchanger.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "First Name")
    private String firstName;
    @Column(name = "Last Name")
    private String lastName;
    @Column(name = "Email address")
    private String email;
    @Column(name = "Password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    Set<Game> myGames;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actualUser")
    Set<Game> borrowedGames;

}
