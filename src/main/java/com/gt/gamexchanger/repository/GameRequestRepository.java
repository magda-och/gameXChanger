package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.RequestGame;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameRequestRepository {
    void addAndSendNewGameRequest(RequestGame requestGame);
    List<RequestGame> getMySendGameRequest(Long fromUserId);
    List<RequestGame> getReceivedGameRequest(Long toUserId);
    void removeGameRequestById(Long gameRequestId);
    RequestGame getRequestById(Long requestId);
    List<RequestGame> getAllRequest();
}
