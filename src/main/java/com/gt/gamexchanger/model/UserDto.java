package com.gt.gamexchanger.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
@Data
public class UserDto {
    private static final AtomicInteger count = new AtomicInteger(0);
    private long id;
    private String name;
    private String lastName;
    private String email;
    private String password;

    private Shelf gamesShelf;

    public UserDto(String name, String lastName, String email, String password) {
        this.id = count.incrementAndGet();
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}