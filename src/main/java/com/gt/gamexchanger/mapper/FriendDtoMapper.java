package com.gt.gamexchanger.mapper;

import com.gt.gamexchanger.dto.FriendDto;
import com.gt.gamexchanger.model.Friend;
import org.springframework.stereotype.Component;

@Component
public class FriendDtoMapper implements DtoMapper<FriendDto, Friend> {
    @Override
    public FriendDto toDto(Friend friend) {
        if (friend == null) {
            return null;
        }
        return new FriendDto(friend.getFirstUserId(), friend.getSecondUserId());
    }

    @Override
    public Friend toDomainObject(FriendDto dtoObject) {
        Friend friend = new Friend();
        friend.setFirstUserId(dtoObject.getFirstUserId());
        friend.setSecondUserId(dtoObject.getSecondUserId());
        return friend;
    }
}
