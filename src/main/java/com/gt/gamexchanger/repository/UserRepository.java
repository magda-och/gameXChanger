package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT users FROM users WHERE LOWER(firstName) = LOWER(:firstName)")
    List<User> searchUsersByFirstName(@Param("firstName") String firstName);
// zmienic na ignore case --- sprawdzić
    @Query("SELECT users FROM users WHERE LOWER(lastName) = LOWER(:lastName)")
    List<User> searchUsersByLastName(@Param("lastName") String lastName);

    @Query("SELECT users FROM users WHERE LOWER(firstName) = LOWER(:firstName)"
            +" AND LOWER(lastName) = LOWER(:lastName)")
    List<User> searchUsersByFirstNameAndLastName(@Param("firstName") String firstName,
                                          @Param ("lastName") String lastName);

    Optional<User> findUserByEmail(String email);

}
