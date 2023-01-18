package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.RequestGame;
import com.gt.gamexchanger.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class GameRequestRepositoryImp implements GameRequestRepository {
    private final Map<Long, RequestGame> inMemoryRequestGames = new HashMap<>();

    //metody tu i w interface
    //addRequest
    //deleteRequest
    //getAllSendRequest
    //getAllReceiveRequest
    //
}
