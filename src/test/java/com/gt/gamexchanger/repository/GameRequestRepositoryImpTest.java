package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.enums.RequestStatus;
import com.gt.gamexchanger.model.RequestGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameRequestRepositoryImpTest {

    GameRequestRepositoryImp gameRequestRepositoryImp;
    RequestGame requestGame;
    RequestGame requestGame2;
    RequestGame requestGame3;

    @BeforeEach
    void init() {
        gameRequestRepositoryImp = new GameRequestRepositoryImp();
        requestGame = new RequestGame(1L, RequestStatus.WAITING, 1L, 1L, 1, "abc");
        requestGame2 = new RequestGame(2L,RequestStatus.ACCEPTED, 2L, 1L, 2, "abc");
        requestGame3 = new RequestGame(3L,RequestStatus.WAITING, 1L, 2L, 3, "abc");

        gameRequestRepositoryImp.addAndSendNewGameRequest(requestGame);
        gameRequestRepositoryImp.addAndSendNewGameRequest(requestGame2);
        gameRequestRepositoryImp.addAndSendNewGameRequest(requestGame3);
    }

    @Test
    void addAndSendNewGameRequest() {
        Long userId = 1L;
        assertTrue(requestGame.getRequestGameId() == userId);
        List<RequestGame> list = gameRequestRepositoryImp.getMySendGameRequest(userId);
        assertEquals(2, list.size());
        assertTrue(list.get(0).getRequestStatus() == RequestStatus.WAITING);}

    @Test
    void getMySendGameRequest() {
        assertEquals(2,gameRequestRepositoryImp.getMySendGameRequest(1L).size());
        assertEquals(1,gameRequestRepositoryImp.getMySendGameRequest(2L).size());

    }

    @Test
    void getReceivedGameRequest() {
        assertEquals(2,gameRequestRepositoryImp.getReceivedGameRequest(1L).size());
        assertEquals(1,gameRequestRepositoryImp.getReceivedGameRequest(2L).size());
    }

    @Test
    void removeGameRequestById() {
        gameRequestRepositoryImp.removeGameRequestById(1L);
        assertEquals(2,gameRequestRepositoryImp.getAllRequest().size());
        gameRequestRepositoryImp.removeGameRequestById(2L);
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
