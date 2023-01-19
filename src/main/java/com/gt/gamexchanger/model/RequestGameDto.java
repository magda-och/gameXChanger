package com.gt.gamexchanger.model;

import com.gt.gamexchanger.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestGameDto {

    private long requestGameId;
    private RequestStatus requestStatus;
    private long fromUserId;
    private long toUserId;
    private long gameId;

    public RequestGameDto(RequestStatus requestStatus, long fromUserId, long toUserId, long gameId) {
        this.requestStatus = requestStatus;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.gameId = gameId;
    }
}
