package com.gt.gamexchanger.service;

import com.gt.gamexchanger.dto.FriendDto;
import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.Friend;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.repository.FriendRepository;
import com.gt.gamexchanger.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    private final DtoMapper<FriendDto, Friend> friendDtoMapper;
    private final DtoMapper<UserDto, User> userDtoMapper;

    public FriendService(FriendRepository friendRepository,
                         UserRepository userRepository, DtoMapper<FriendDto, Friend> friendDtoMapper, DtoMapper<UserDto, User> userDtoMapper) {
        this.friendRepository = friendRepository;
        this.friendDtoMapper = friendDtoMapper;
        this.userDtoMapper = userDtoMapper;
        this.userRepository = userRepository;
    }

    public void saveFriend(UserDto userDto1, long id) throws NullPointerException {

        User user = userRepository.findUserById(id);

        UserDto userDto2 = userDtoMapper.toDto(user);

        Friend friend = new Friend();
        User user1 = userRepository.findUserByEmail(userDto1.getEmail());
        User user2 = userRepository.findUserByEmail(userDto2.getEmail());
        User firstUser = user1;
        User secondUser = user2;
        if (user1.getId() > user2.getId()) {
            firstUser = user2;
            secondUser = user1;
        }

        if (!(friendRepository.existsByFirstUserAndSecondUser(firstUser, secondUser))) {
            friend.setCreatedDate(new Date());
            friend.setFirstUser(firstUser);
            friend.setSecondUser(secondUser);
            friendRepository.save(friend);
        }
    }

    public List<UserDto> getFriends(Long id) {
        User currentUser = userRepository.findUserById(id);
        List<Friend> friendsByFirstUser = friendRepository.findByFirstUser(currentUser);
        List<Friend> friendsBySecondUser = friendRepository.findBySecondUser(currentUser);
        List<User> friendUsers = new ArrayList<>();
        for (Friend friend : friendsByFirstUser) {
            friendUsers.add(userRepository.findUserById(friend.getSecondUser().getId()));
        }
        for (Friend friend : friendsBySecondUser) {
            friendUsers.add(userRepository.findUserById(friend.getFirstUser().getId()));
        }
        return friendUsers.stream().map(userDtoMapper::toDto).collect(Collectors.toList());
    }

    public void deleteFriends(Long valueOf) {

    }
}

