package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.Friend;
import com.gt.gamexchanger.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class FriendRepositoryImp implements FriendRepository {

    private final Map<Long, Friend> inMemoryFriends = new HashMap<>();
    @Override
    public boolean existByFirstUserAndSecondUser(User first, User second) {
        return inMemoryFriends.values().stream()
                .allMatch(friend -> friend.getFirstUser().equals(first)
                        && friend.getSecondUser().equals(second));
    }

    @Override
    public List<Friend> findByFirstUser(User user) {
        return inMemoryFriends.values().stream()
                .filter(friend -> friend.getFirstUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<Friend> findBySecondUser(User user) {
        return inMemoryFriends.values().stream()
                .filter(friend -> friend.getSecondUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public void saveFriend(Friend friend) {
        friend.setId((long) (inMemoryFriends.size()+1));
        inMemoryFriends.put(friend.getId(), friend);
    }

    @Override
    public List<Friend> getFriends() {
        return new ArrayList<>(inMemoryFriends.values());
    }

    @Override
    public Friend getFriendById(Long id) {
        return inMemoryFriends.get(id);
    }

    @Override
    public void removeFriendById(Long id) {
        inMemoryFriends.remove(id);
    }
}
