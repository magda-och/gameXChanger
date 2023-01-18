package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    void addUser(User user);
    List<User> findUserByFullName(String name, String lastName);
    List<User> findUserByFirstName(String firstName);
    List<User> findUserByLastName(String lastName);
    void deleteUser(Long id);
}
