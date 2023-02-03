package com.gt.gamexchanger.service;

import com.gt.gamexchanger.dto.FriendDto;
import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.exception.NoExistingFriend;
import com.gt.gamexchanger.exception.NoExistingUser;
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

  //  private final FriendRequestRepository friendRequestRepository;

    private final DtoMapper<FriendDto, Friend> friendDtoMapper;
    private final DtoMapper<UserDto, User> userDtoMapper;

    public FriendService(FriendRepository friendRepository,
                         UserRepository userRepository,
                         DtoMapper<FriendDto, Friend> friendDtoMapper,
                         DtoMapper<UserDto, User> userDtoMapper/*,
                         FriendRequestRepository friendRequestRepository*/) {
        this.friendRepository = friendRepository;
        this.friendDtoMapper = friendDtoMapper;
        this.userDtoMapper = userDtoMapper;
        this.userRepository = userRepository;
    //    this.friendRequestRepository = friendRequestRepository;
    }

    public Friend saveFriend(UserDto userDto1, long id) throws NullPointerException {

        Optional<User> userOptional = userRepository.findUserById(id);
        if(userOptional.isEmpty()){
            throw  new RuntimeException("no such user");
        }
        User user = userOptional.get();
        UserDto userDto2 = userDtoMapper.toDto(user);

        Friend friend = new Friend();
        Optional<User> user1 = userRepository.findUserByEmail(userDto1.getEmail());
        Optional<User> user2 = userRepository.findUserByEmail(userDto2.getEmail());
        if(user1.isEmpty() || user2.isEmpty()){
            throw new RuntimeException("no such users");
        }
        User firstUser = user1.get();
        User secondUser = user2.get();
        if (user1.get().getId() > user2.get().getId()) {
            firstUser = user2.get();
            secondUser = user1.get();
        }

        if (!(friendRepository.existsByFirstUserAndSecondUser(firstUser, secondUser))) {
            /*if(friendRequestRepository.*/
                friend.setCreatedDate(new Date());
                friend.setFirstUser(firstUser);
                friend.setSecondUser(secondUser);

        }
        return friendRepository.save(friend);
    }

    public List<UserDto> getFriends(Long id) {
        Optional<User> currentUserOptional = userRepository.findUserById(id);
        if(currentUserOptional.isEmpty()){
            throw  new NoExistingUser();
        }
        User currentUser = currentUserOptional.get();
        List<Friend> friendsByFirstUser = friendRepository.findByFirstUser(currentUser);
        List<Friend> friendsBySecondUser = friendRepository.findBySecondUser(currentUser);
        List<User> friendUsers = new ArrayList<>();
        for (Friend friend : friendsByFirstUser) {
            Optional<User> user1Optional = userRepository.findUserById(friend.getSecondUser().getId());
            user1Optional.ifPresent(friendUsers::add);
        }
        for (Friend friend : friendsBySecondUser) {
            Optional<User> user2Optional = userRepository.findUserById(friend.getFirstUser().getId());
            user2Optional.ifPresent(friendUsers::add);
        }
        if(friendUsers.isEmpty()){
            throw new NoExistingFriend();
        }
        return friendUsers.stream().map(userDtoMapper::toDto).collect(Collectors.toList());
    }

    public void deleteFriends(Long userId, Long currentUserId) {
        Optional<User> user = userRepository.findUserById(userId);
        Optional<User> currentUser = userRepository.findUserById(currentUserId);

        if(user.isPresent() && currentUser.isPresent()){
            if(friendRepository.existsByFirstUserAndSecondUser(user.get(), currentUser.get())){
                Friend friend = friendRepository.findFriendByFirstUserAndSecondUser(user.get(), currentUser.get());
                friendRepository.delete(friend);
                friendRepository.flush();
            }
        }else{
            throw new NoExistingFriend();
        }
    }
}

