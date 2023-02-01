package com.gt.gamexchanger.dto;

import com.gt.gamexchanger.enums.RequestStatus;
import com.gt.gamexchanger.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestFriendDto {

    private Long requestFriendId;
    private RequestStatus requestStatus;
    private User fromUserId;
    private User toUserId;
    private String message;

    public RequestFriendDto(RequestStatus requestStatus, User fromUserId, User toUserId, String message) {
        this.requestStatus = requestStatus;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.message=message;
    }
}
