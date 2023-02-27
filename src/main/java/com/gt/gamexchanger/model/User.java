package com.gt.gamexchanger.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(max = 20)
    private String firstName;
    @NotBlank
    @Size(max = 30)
    private String lastName;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(max = 120)
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
 /*   @JoinTable(	name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))*/
    private Set<Role> roles = new HashSet<>();
}
