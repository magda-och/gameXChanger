package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.Friend;
import com.gt.gamexchanger.model.User;

import java.util.List;

public class FriendRepositoryImp implements FriendRepository {
    @Override
    public boolean existByFirstUserAndSecondUser(User first, User second) {
        return false;
    }

    @Override
    public List<Friend> findByFirstUser(User user) {
        return null;
    }

    @Override
    public List<Friend> findBySecondUser(User user) {
        return null;
    }
}
