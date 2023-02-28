package com.gt.gamexchanger.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String city;
    private int phoneNumber;
    @JsonManagedReference("myGames")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    List<Game> myGames;
    @JsonManagedReference("borrowedGames")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actualUser")
    List<Game> borrowedGames;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fromUserId")
    @JsonManagedReference("sendRequests")
    List<RequestFriend> sendRequests;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "toUserId")
    @JsonManagedReference("receivedRequests")
    List<RequestFriend> receivedRequests;
    @ManyToMany
    @JsonIncludeProperties("id")
    List<User> friends;


}
