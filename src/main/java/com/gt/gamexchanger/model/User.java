package com.gt.gamexchanger.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
//    private Shelf gamesShelf = new Shelf();

    public User(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
