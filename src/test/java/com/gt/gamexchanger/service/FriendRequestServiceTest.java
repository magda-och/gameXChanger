package com.gt.gamexchanger.service;

import com.gt.gamexchanger.dto.RequestFriendDto;
import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.enums.RequestStatus;
import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.model.RequestFriend;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.repository.FriendRequestRepository;
import com.gt.gamexchanger.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
       // friendRequestService.addFriendRequest(requestFriendDto);
       // verify(friendRequestRepository).save(Mockito.isA(RequestFriend.class));
    }
    @Test
    void getAllRequest() {
        RequestFriend rf1 = new RequestFriend(1L, RequestStatus.WAITING,null,null,"cos");
        RequestFriend rf2 = new RequestFriend(2L,RequestStatus.WAITING,null,null,"cos2");
        List<RequestFriend> r= Arrays.asList(rf1, rf2);

        RequestFriendDto rfd1 = new RequestFriendDto(1L, RequestStatus.WAITING,null,null,"cos");
        RequestFriendDto rfd2 = new RequestFriendDto(2L,RequestStatus.WAITING,null,null,"cos2");
        List<RequestFriendDto> expected=Arrays.asList(rfd1,rfd2);

        when(friendRequestRepository.getAllRequest()).thenReturn(r);
        when(dtoMapper.toDto(rf1)).thenReturn(rfd1);
        when(dtoMapper.toDto(rf2)).thenReturn(rfd2);
        when(friendRequestRepository.getAllRequest()).thenReturn(r);;

        assertEquals(expected, friendRequestService.getAllRequest());

        verify(friendRequestRepository, times(1)).getAllRequest();
        verify(dtoMapper, times(2)).toDto(Mockito.isA(RequestFriend.class));
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