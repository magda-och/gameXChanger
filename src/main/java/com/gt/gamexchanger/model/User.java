package com.gt.gamexchanger.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
@Data
public class User {
    private long id;
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
