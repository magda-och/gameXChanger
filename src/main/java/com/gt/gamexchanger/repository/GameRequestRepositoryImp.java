package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.RequestGame;
import com.gt.gamexchanger.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GameRequestRepositoryImp implements GameRequestRepository {
    private final Map<Long, RequestGame> inMemoryRequestGames = new HashMap<>();

    @Override
    public void addAndSendNewGameRequest(RequestGame requestGame) {

    }

    @Override
    public List<RequestGame> getMySendGameRequest() {
        return null;
    }

    @Override
    public List<RequestGame> getReceivedGameRequest() {
        return null;
    }

    @Override
    public void removeGameRequestById(UUID gameRequestId) {

    }

    @Override
    public RequestGame getRequestById(UUID requestId) {
        return null;
    }

    @Override
    public List<RequestGame> getAllRequest() {
        return null;
    }


    //metody tu i w interface
    //addRequest
    //deleteRequest
    //getAllSendRequest
    //getAllReceiveRequest
    //
}
