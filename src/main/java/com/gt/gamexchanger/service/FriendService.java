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

        User firstuser = user1;
        User seconduser = user2;
        if(user1.getId() > user2.getId()){
            firstuser = user2;
            seconduser = user1;
        }
        if(!friendRepository.existByFirstUserAndSecondUser(firstuser, seconduser)){
            friend.setCreatedDate(new Date());
            friend.setFirstUser(firstuser);
            friend.setSecondUser(seconduser);
            friendRepository.saveFriend(friend);
        }

    }

}
