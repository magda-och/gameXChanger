package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    void addUser(User user);
    List<User> findUserByFullName(String name, String lastName);
    List<User> findUserByFirstName(String firstName);
    List<User> findUserByLastName(String lastName);
    void deleteUser(User user);
}
