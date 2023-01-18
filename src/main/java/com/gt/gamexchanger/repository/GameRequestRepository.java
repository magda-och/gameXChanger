package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.RequestGame;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameRequestRepository {
    void addAndSendNewGameRequest(RequestGame requestGame);
    List<RequestGame> getMySendGameRequest();
    List<RequestGame> getReceivedGameRequest();
    void removeGameRequestById(UUID gameRequestId);
    RequestGame getRequestById(UUID requestId);
    List<RequestGame> getAllRequest();
}
