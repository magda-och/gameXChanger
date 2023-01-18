package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    void addUser(User user);
    List<User> findUserByName(String name, String lastName);
    List<User> findUserByName(String lastName);
    void deleteUser(User user);
}
