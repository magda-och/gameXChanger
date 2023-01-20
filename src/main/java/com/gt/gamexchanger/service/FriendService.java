package com.gt.gamexchanger.service;

import com.gt.gamexchanger.dto.FriendDto;
import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.Friend;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.repository.FriendRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final DtoMapper<FriendDto, Friend> friendDtoMapper;
    private final DtoMapper<UserDto, User> userDtoMapper;

    public FriendService(FriendRepository friendRepository, DtoMapper<FriendDto, Friend> friendDtoMapper, DtoMapper<UserDto, User> userDtoMapper) {
        this.friendRepository = friendRepository;
        this.friendDtoMapper = friendDtoMapper;
        this.userDtoMapper = userDtoMapper;
    }

    public void saveFriend(FriendDto friendDto) {

        var friend = friendDtoMapper.toDomainObject(friendDto);
        friendRepository.saveFriend(friend);
        friendDtoMapper.toDto(friend);

    }

    public List<UserDto> getFriends(Long id){
        return friendRepository.getFriends(id).stream().
                map(userDtoMapper::toDto).
                collect(Collectors.toList());
    }

    public boolean removeFriend(Friend friend){
        return friendRepository.removeFriend(friend);
    }

}
