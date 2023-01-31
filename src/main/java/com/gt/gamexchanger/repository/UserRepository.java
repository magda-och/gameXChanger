package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    List<User> getAllUsers();
//    Optional<User> getUserById(Long id);
//    void addUser(User user);
//    void deleteUser(Long id); //sprawdzic czy nie wysypuje
//    List<User> findUserByName(String name);
    List<User> findUsersByName(String name);

    User findUserByEmail(String email);

    User findUserById(Long id);
}
