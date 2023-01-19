package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    void addUser(User user);
    void deleteUser(Long id); //sprawdzic czy nie wysypuje
    List<User> findUserByName(String name);
}
