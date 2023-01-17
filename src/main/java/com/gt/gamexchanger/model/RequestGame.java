package com.gt.gamexchanger.model;

import com.gt.gamexchanger.enums.RequestStatus;

public class RequestGame {
    private long requestGameId;
    private RequestStatus requestStatus;
    private long fromUserId;
    private long toUserId;
    private Game game;

}
