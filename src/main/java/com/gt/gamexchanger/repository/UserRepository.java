package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.User;

import java.util.List;

public interface UserRepository {
    public void addUser(User user);
    List<User> getAllUsers();

    User getUserById(Long id);

}
