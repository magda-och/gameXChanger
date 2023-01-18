package com.gt.gamexchanger.mapper;

import com.gt.gamexchanger.model.RequestGame;
import com.gt.gamexchanger.model.RequestGameDto;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.model.UserDto;

public class RequestGameDtoMapper implements DtoMapper<RequestGameDto, RequestGame>{
    @Override
    public RequestGameDto toDto(RequestGame requestGame) {
        if (requestGame == null) {
            return null;
        }
        RequestGameDto requestGameDto =  new RequestGameDto(requestGame.getRequestStatus(), requestGame.getFromUserId(), requestGame.getToUserId(), requestGame.getGame());
        requestGameDto.setRequestGameId(requestGame.getRequestGameId());
        return requestGameDto;
    }

    @Override
    public RequestGame toDomainObject(RequestGameDto requestGameDto) {

        RequestGame requestGame = new RequestGame();
        requestGame.setRequestGameId(requestGameDto.getRequestGameId());
        requestGame.setRequestStatus(requestGameDto.getRequestStatus());
        requestGame.setFromUserId(requestGameDto.getFromUserId());
        requestGame.setToUserId(requestGameDto.getToUserId());
        requestGame.setGame(requestGameDto.getGame());
        return requestGame;
    }
}
