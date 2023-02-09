package com.gt.gamexchanger.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "users")
public class User {

    //todo dodać miasto do usera lub/i nr tel
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String city;
    private int phoneNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    List<Game> myGames;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actualUser")
    List<Game> borrowedGames;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromUserId")
    List<RequestFriend> sendRequests;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toUserId")
    List<RequestFriend> receivedRequests;



}
