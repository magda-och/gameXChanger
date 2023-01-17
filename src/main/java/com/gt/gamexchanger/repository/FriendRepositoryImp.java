package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.Friend;
import com.gt.gamexchanger.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FriendRepositoryImp implements FriendRepository {

    private final Map<Long, Friend> inMemoryFriends = new HashMap<>();
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

    @Override
    public void saveFriend(Friend friend) {
        friend.setId((long) (inMemoryFriends.size()+1));
        inMemoryFriends.put(friend.getId(), friend);
    }

    @Override
    public List<Friend> getFriends() {
        return null;
    }

    @Override
    public Friend getFriendById(Long id) {
        return null;
    }

    @Override
    public void removeFriendById(Long id) {

    }
}
