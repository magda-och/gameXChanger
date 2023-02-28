package com.gt.gamexchanger.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "users")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
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
    @OneToMany
    @JsonIncludeProperties("id")
    List<User> friends;

    @ManyToMany(fetch = FetchType.LAZY)
/*    @JoinTable(	name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))*/
    private Set<Role> roles = new HashSet<>();
}
