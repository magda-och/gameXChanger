package com.gt.gamexchanger.service;

import com.gt.gamexchanger.dto.RequestFriendDto;
import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.RequestFriend;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.repository.FriendRequestRepository;
import com.gt.gamexchanger.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class FriendRequestServiceTest {
    private FriendRequestService friendRequestService;
    private AutoCloseable autoCloseable;
    @Mock
    private FriendRequestRepository friendRequestRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private DtoMapper<RequestFriendDto, RequestFriend> dtoMapper;
    @Mock
    private RequestFriendDto requestFriendDto;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        requestFriendDto = new RequestFriendDto();
        friendRequestService = new FriendRequestService(friendRequestRepository, userRepository,dtoMapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void addFriendRequest() {
      //  friendRequestService.addFriendRequest(requestFriendDto);
        //verify(friendRequestRepository).save(requestFriendDto.)
    }
    @Test
    void getAllRequest() {
        friendRequestService.getAllRequest();
        verify(friendRequestRepository).getAllRequest();
    }

    @Test
    void getMySendFriendRequest() {
        friendRequestService.getMySendFriendRequest(requestFriendDto.getRequestFriendId());
        verify(friendRequestRepository).getRequestFriendsByFromUserId(requestFriendDto.getRequestFriendId());
    }

    @Test
    void getReceivedFriendRequest() {
        friendRequestService.getReceivedFriendRequest(requestFriendDto.getRequestFriendId());
        verify(friendRequestRepository).getRequestFriendsByToUserId(requestFriendDto.getRequestFriendId());
    }

    @Test
    void updateStatus() {
    }

    @Test
    void removeFriendRequestById() {
    }






}