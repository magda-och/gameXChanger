package com.gt.gamexchanger.model;

import com.gt.gamexchanger.enums.RequestStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RequestGame {
    //chyba jeszcze data od kiedy do kiedy chcemy wypożyczyc albo message
    private long requestGameId;
    private RequestStatus requestStatus;
    private long fromUserId;
    private long toUserId;
    private Game game;

    public RequestGame(RequestStatus requestStatus, long fromUserId, long toUserId, Game game) {
        this.requestStatus = requestStatus;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.game = game;
    }
}
