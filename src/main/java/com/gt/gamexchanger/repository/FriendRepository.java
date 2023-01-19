package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.Friend;
import com.gt.gamexchanger.model.User;

import java.util.List;

public interface FriendRepository {

    void saveFriend(Friend friend);
    List<User> getFriends(Long id);
    Friend getFriendById(Long id);
    void removeFriendById(Long id);

}
