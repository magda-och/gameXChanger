package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.Friend;
import com.gt.gamexchanger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
@Component
public class FriendRepositoryImp implements FriendRepository {

    private final Map<Long, Set<Long>> inMemoryFriends = new HashMap<>();
    final UserRepository userRepository;
    public FriendRepositoryImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveFriend(Friend friend) {
        Long userId = friend.getFirstUserId();
        Long friendId = friend.getSecondUserId();

        if(userRepository.getUserById(userId).isEmpty()){
            throw new RuntimeException();
        }
        inMemoryFriends.computeIfAbsent(userId, uId -> new HashSet<>()).add(friendId);
        inMemoryFriends.computeIfAbsent(friendId, fId -> new HashSet<>()).add(userId);
    }

    @Override
    public List<User> getFriends(Long id) {
        Set<Long> myFriends = inMemoryFriends.get(id);

        return myFriends.stream()
                .map(e -> userRepository.getUserById(e))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public Friend getFriendById(Long id) {
        return null;
    }

    @Override
    public void removeFriendById(Long id) {
        inMemoryFriends.remove(id);
    }
}
