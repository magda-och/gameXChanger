package com.gt.gamexchanger.repository;
/*
import com.gt.gamexchanger.enums.RequestStatus;
import com.gt.gamexchanger.model.RequestFriend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameRequestRepositoryImpTest {

    FriendRequestRepositoryImp gameRequestRepositoryImp;
    RequestFriend requestGame;
    RequestFriend requestGame2;
    RequestFriend requestGame3;

    @BeforeEach
    void init() {
        gameRequestRepositoryImp = new FriendRequestRepositoryImp();
        requestGame = new RequestFriend(1L, RequestStatus.WAITING, 1L, 1L, "abc");
        requestGame2 = new RequestFriend(2L,RequestStatus.ACCEPTED, 2L, 1L, "abc");
        requestGame3 = new RequestFriend(3L,RequestStatus.WAITING, 1L, 2L, "abc");

        gameRequestRepositoryImp.addAndSendNewFriendRequest(requestGame);
        gameRequestRepositoryImp.addAndSendNewFriendRequest(requestGame2);
        gameRequestRepositoryImp.addAndSendNewFriendRequest(requestGame3);
    }

    @Test
    void addAndSendNewGameRequest() {
        Long userId = 1L;
        assertTrue(requestGame.getRequestFriendId() == userId);
        List<RequestFriend> list = gameRequestRepositoryImp.getMySendFriendRequest(userId);
        assertEquals(2, list.size());
        assertTrue(list.get(0).getRequestStatus() == RequestStatus.WAITING);}

    @Test
    void getMySendGameRequest() {
        assertEquals(2,gameRequestRepositoryImp.getMySendFriendRequest(1L).size());
        assertEquals(1,gameRequestRepositoryImp.getMySendFriendRequest(2L).size());

    }

    @Test
    void getReceivedGameRequest() {
        assertEquals(2,gameRequestRepositoryImp.getReceivedFriendRequest(1L).size());
        assertEquals(1,gameRequestRepositoryImp.getReceivedFriendRequest(2L).size());
    }

    @Test
    void removeGameRequestById() {
        gameRequestRepositoryImp.removeFriendRequestById(1L);
        assertEquals(2,gameRequestRepositoryImp.getAllRequest().size());
        gameRequestRepositoryImp.removeFriendRequestById(2L);
        assertEquals(1,gameRequestRepositoryImp.getAllRequest().size());

    }

    @Test
    void getRequestById() {
        assertEquals(requestGame,gameRequestRepositoryImp.getRequestById(1L));
        assertEquals(requestGame2,gameRequestRepositoryImp.getRequestById(2L));
        assertEquals(requestGame3,gameRequestRepositoryImp.getRequestById(3L));

    }

    @Test
    void getAllRequest() {
        assertEquals(3,gameRequestRepositoryImp.getAllRequest().size());
    }
}
*/

