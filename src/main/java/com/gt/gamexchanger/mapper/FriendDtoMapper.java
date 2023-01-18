package com.gt.gamexchanger.mapper;

import com.gt.gamexchanger.dto.FriendDto;
import com.gt.gamexchanger.model.Friend;

public class FriendDtoMapper implements DtoMapper<FriendDto, Friend> {
    @Override
    public FriendDto toDto(Friend friend) {
        if (friend == null) {
            return null;
        }
        Long id = friend.getId();
        return new FriendDto(id, friend.getCreatedDate(), friend.getFirstUser(), friend.getSecondUser());
    }

    @Override
    public Friend toDomainObject(FriendDto dtoObject) {
        return null;
    }
}
