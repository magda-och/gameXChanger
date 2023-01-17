package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.Friend;
import com.gt.gamexchanger.model.User;

import java.util.List;
import java.util.UUID;

public interface FriendRepository {
    boolean existByFirstUserAndSecondUser(User first, User second);

    List<Friend> findByFirstUser(User user);
    List<Friend> findBySecondUser(User user);

    void saveFriend(Friend friend);
    List<Friend> getFriends();
    Friend getFriendById(Long id);
    void removeFriendById(Long id);

}
