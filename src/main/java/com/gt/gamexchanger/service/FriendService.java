package com.gt.gamexchanger.service;

import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.Friend;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.model.UserDto;
import com.gt.gamexchanger.repository.FriendRepository;
import com.gt.gamexchanger.repository.UserRepository;

import java.util.Date;


public class FriendService {

    private FriendRepository friendRepository;
    private UserRepository userRepository;
    private DtoMapper<UserDto, User> dtoMapper;

    public void saveFriend(UserDto userDto1, long id) throws NullPointerException{

        User user = userRepository.getUserById(id);
        var userDto2 = dtoMapper.toDto(user);

        Friend friend = new Friend();
        User user1 = userRepository.getUserById(userDto1.getId());
        User user2 = userRepository.getUserById(userDto2.getId());

        User firstUser = user1;
        User secondUser = user2;
        if(user1.getId() > user2.getId()){
            firstUser = user2;
            secondUser = user1;
        }
        if(!friendRepository.existByFirstUserAndSecondUser(firstUser, secondUser)){
            friend.setCreatedDate(new Date());
            friend.setFirstUser(firstUser);
            friend.setSecondUser(secondUser);
            friendRepository.saveFriend(friend);
        }

    }

}
