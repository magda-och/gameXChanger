package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    public void addUser(User user);
}
