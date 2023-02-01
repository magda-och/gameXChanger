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

    @Query("SELECT user FROM User user WHERE LOWER(user.firstName) = LOWER(:firstName)")
    List<User> searchUsersByFirstName(@Param("firstName") String firstName);

    @Query("SELECT user FROM User user WHERE LOWER(user.lastName) = LOWER(:lastName)")
    List<User> searchUsersByLastName(@Param("lastName") String lastName);

    @Query("SELECT user FROM User user WHERE LOWER(user.firstName) = LOWER(:firstName)"
            +" AND LOWER(user.lastName) = LOWER(:lastName)")
    List<User> searchUsersByFirstNameAndLastName(@Param("firstName") String firstName,
                                          @Param ("lastName") String lastName);



    Optional<User> findByEmail(String mail);
}
