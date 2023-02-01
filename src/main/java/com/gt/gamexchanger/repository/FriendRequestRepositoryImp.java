
package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.RequestFriend;
import org.springframework.stereotype.Component;

import java.util.*;
/*
@Component
public class FriendRequestRepositoryImp implements FriendRequestRepository {
    private final Map<Long, RequestFriend> inMemoryRequestFriends = new HashMap<>();

    @Override
    public void addAndSendNewFriendRequest(RequestFriend requestFriend) {
        requestFriend.setRequestFriendId(inMemoryRequestFriends.values().size()+1);
        inMemoryRequestFriends.put(requestFriend.getRequestFriendId(), requestFriend);
    }

    @Override
    public List<RequestFriend> getMySendFriendRequest(Long userId) {
       return inMemoryRequestFriends.values().stream()
                .filter((RequestFriend p) -> p.getFromUserId()==userId)
                .toList();
    }

    @Override
    public List<RequestFriend> getReceivedFriendRequest(Long userId) {
        return inMemoryRequestFriends.values().stream()
                .filter((RequestFriend p) -> p.getFromUserId()==userId)
                .toList();
    }

    @Override
    public void removeFriendRequestById(Long friendRequestId) {
        inMemoryRequestFriends.remove(friendRequestId);
    }

    @Override
    public RequestFriend getRequestById(Long requestId) {
        return inMemoryRequestFriends.get(requestId);
    }

    @Override
    public List<RequestFriend> getAllRequest() {
        return new ArrayList<>(inMemoryRequestFriends.values());
    }
}*/
