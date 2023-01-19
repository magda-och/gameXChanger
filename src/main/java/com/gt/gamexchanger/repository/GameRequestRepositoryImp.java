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
        requestGame.setRequestGameId(inMemoryRequestGames.values().size()+1);
        inMemoryRequestGames.put(requestGame.getRequestGameId(), requestGame);
    }

    @Override
    public List<RequestGame> getMySendGameRequest(Long userId) {
       return inMemoryRequestGames.values().stream()
                .filter((RequestGame p) -> p.getFromUserId()==userId)
                .toList();
    }

    @Override
    public List<RequestGame> getReceivedGameRequest(Long userId) {
        return inMemoryRequestGames.values().stream()
                .filter((RequestGame p) -> p.getFromUserId()==userId)
                .toList();
    }

    @Override
    public void removeGameRequestById(Long gameRequestId) {
        inMemoryRequestGames.remove(gameRequestId);
    }

    @Override
    public RequestGame getRequestById(Long requestId) {
        return inMemoryRequestGames.get(requestId);
    }

    @Override
    public List<RequestGame> getAllRequest() {
        return new ArrayList<>(inMemoryRequestGames.values());
    }
}
