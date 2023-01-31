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
    private String name;
    private String lastName;
    private String email;
    private String password;
//    private Shelf gamesShelf = new Shelf();

//    public User(String name, String lastName, String email, String password) {
//        this.name = name;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//    }
}
