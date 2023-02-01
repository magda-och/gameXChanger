package com.gt.gamexchanger.mapper;

import com.gt.gamexchanger.model.RequestFriend;
import com.gt.gamexchanger.dto.RequestFriendDto;
import org.springframework.stereotype.Component;

@Component
public class RequestFriendDtoMapper implements DtoMapper<RequestFriendDto, RequestFriend>{
    @Override
    public RequestFriendDto toDto(RequestFriend requestFreind) {
        if (requestFreind == null) {
            return null;
        }
        RequestFriendDto requestFriendDto =  new RequestFriendDto(requestFreind.getRequestStatus(), requestFreind.getFromUserId(), requestFreind.getToUserId(), requestFreind.getMessage());
        requestFriendDto.setRequestFriendId(requestFreind.getRequestFriendId());
        return requestFriendDto;
    }

    @Override
    public RequestFriend toDomainObject(RequestFriendDto requestFriendDto) {
        RequestFriend requestFriend = new RequestFriend();
        requestFriend.setRequestFriendId(requestFriendDto.getRequestFriendId());
        requestFriend.setRequestStatus(requestFriendDto.getRequestStatus());
        requestFriend.setFromUserId(requestFriendDto.getFromUserId());
        requestFriend.setToUserId(requestFriendDto.getToUserId());
        requestFriend.setMessage(requestFriendDto.getMessage());
        return requestFriend;
    }
}
