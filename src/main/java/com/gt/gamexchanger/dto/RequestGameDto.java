package com.gt.gamexchanger.dto;

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
    private String message;

    public RequestGameDto(RequestStatus requestStatus, long fromUserId, long toUserId, long gameId, String message) {
        this.requestStatus = requestStatus;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.gameId = gameId;
        this.message=message;
    }
}
