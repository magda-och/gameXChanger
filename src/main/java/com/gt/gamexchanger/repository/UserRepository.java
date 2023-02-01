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
//
//    @Query("SELECT user from User user WHERE(:firstName is null or LOWER(user.firstName) = LOWER(:firstName))"
//            +" and (:lastName is null or user.lastName = :lastName)")
//    List<User> searchByFirstAndOrLastName(@Param("firstName") String firstName,
//                                          @Param ("lastName") String lastName);
//}

    Optional<User> findUserByEmail(String email);

    User findUserById(Long id);
}
