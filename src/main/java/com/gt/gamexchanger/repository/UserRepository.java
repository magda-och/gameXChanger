package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;

@Repository
public interface   UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM users user WHERE LOWER(user.firstName) = LOWER(:firstName)")
    HashSet<User> searchUsersByFirstName(@Param("firstName") String firstName);
// zmienic na ignore case --- sprawdzić
    @Query("SELECT user FROM users user WHERE LOWER(user.lastName) = LOWER(:lastName)")
    HashSet<User> searchUsersByLastName(@Param("lastName") String lastName);

    @Query("SELECT user FROM users user WHERE LOWER(user.firstName) = LOWER(:firstName)"
            +" AND LOWER(user.lastName) = LOWER(:lastName)")
    HashSet<User> searchUsersByFirstNameAndLastName(@Param("firstName") String firstName,
                                          @Param ("lastName") String lastName);

    Optional<User> findUserByEmail(String email);

    @Query("SELECT user.friends FROM users user")
    List<User> findUserFriends(Long userId);

}
