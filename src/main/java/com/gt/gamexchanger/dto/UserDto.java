package com.gt.gamexchanger.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
@Data
public class UserDto {
    private long id;
    private String name;
    private String lastName;
    private String email;
    private String password;

//    private Shelf gamesShelf = new Shelf();

    public UserDto(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}