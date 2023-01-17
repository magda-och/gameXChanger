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
    private static final AtomicInteger count = new AtomicInteger(0);
    private long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Shelf gamesShelf;
    private List<RequestGame> requestGameList;

    public User(String name, String lastName, String email, String password) {
        this.id = count.incrementAndGet();
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
