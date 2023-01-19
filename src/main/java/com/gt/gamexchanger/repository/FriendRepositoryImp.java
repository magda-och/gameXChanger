package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.Friend;
import com.gt.gamexchanger.model.User;
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
    public void saveFriend(Friend friend) throws RuntimeException {
        Long userId = friend.getFirstUserId();
        Long friendId = friend.getSecondUserId();

        if(userRepository.getUserById(userId).isEmpty()){
            throw new RuntimeException("There is no user with this id.");
        }
        //TODO: info when same friend
        inMemoryFriends.computeIfAbsent(userId, uId -> new HashSet<>()).add(friendId);
        inMemoryFriends.computeIfAbsent(friendId, fId -> new HashSet<>()).add(userId);
    }

    @Override
    public List<User> getFriends(Long id) throws RuntimeException {

        Set<Long> myFriends = inMemoryFriends.get(id);

        if(myFriends == null || myFriends.isEmpty() || userRepository.getUserById(id).isEmpty()){
            throw new RuntimeException("There is no user with this id.");
        }

        return myFriends.stream()
                .map(userRepository::getUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }


    @Override
    public boolean removeFriend(Friend friend) {

        Long userId = friend.getFirstUserId();
        Long friendId = friend.getSecondUserId();

        Set<Long> myFriends = inMemoryFriends.get(userId);

        if(myFriends.isEmpty()) {
            throw new RuntimeException("There is no user with this id.");
        }
        return myFriends.remove(friendId);
    }
}
