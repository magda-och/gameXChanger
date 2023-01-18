package com.gt.gamexchanger.service;

import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.RequestGame;
import com.gt.gamexchanger.model.RequestGameDto;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.model.UserDto;
import com.gt.gamexchanger.repository.GameRequestRepository;
import com.gt.gamexchanger.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GameRequestService {
    private final GameRequestRepository gameRequestRepository;
    private final DtoMapper<RequestGameDto, RequestGame> dtoMapper;

    public GameRequestService(GameRequestRepository gameRequestRepository, DtoMapper<RequestGameDto, RequestGame> dtoMapper) {
        this.gameRequestRepository = gameRequestRepository;
        this.dtoMapper = dtoMapper;
    }


    //addRequest
    //deleteRequest
    //getAllSendRequest
    //getAllReceiveRequest
    //updateRequestStatus
    //
}
