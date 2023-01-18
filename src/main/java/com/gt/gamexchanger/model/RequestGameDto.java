package com.gt.gamexchanger.model;

import com.gt.gamexchanger.enums.RequestStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RequestGameDto {
    private long requestGameId;
    private RequestStatus requestStatus;
    private long fromUserId;
    private long toUserId;
    private Game game;

    public RequestGameDto(RequestStatus requestStatus, long fromUserId, long toUserId, Game game) {
        this.requestStatus = requestStatus;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.game = game;
    }
}
