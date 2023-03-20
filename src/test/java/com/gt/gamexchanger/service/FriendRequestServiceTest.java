package com.gt.gamexchanger.service;

import com.gt.gamexchanger.dto.RequestFriendDto;
import com.gt.gamexchanger.enums.RequestStatus;
import com.gt.gamexchanger.enums.Role;
import com.gt.gamexchanger.exception.NoRequestExistException;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        when(friendRequestRepository.getAllRequest()).thenReturn(r);

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
    void updateStatus_NoRequestExist_ShouldThrowNoRequestExistException() {
        when(friendRequestRepository.getRequestFriendByRequestFriendId(1L)).thenReturn(Optional.empty());

        assertThrows(NoRequestExistException.class, () -> friendRequestService.updateStatus(1L, RequestStatus.ACCEPTED));
    }

    @Test
    void updateStatus_RequestIsAccepted_ShouldReturnRequestFriendAndAddFriend() {
        User testedUser = new User();
        testedUser.setId(1L);
        testedUser.setFirstName("Jan");
        testedUser.setLastName("Kowalski");
        testedUser.setEmail("jan.kowalski@wp.pl");
        testedUser.setPassword("janek");
        testedUser.setRole(Role.USER);
        testedUser.setFriends(new ArrayList<>());

        User friend = new User();
        friend.setId(2L);
        friend.setFirstName("Jan");
        friend.setLastName("Fasola");
        friend.setEmail("jasFasola@com.pl");
        friend.setPassword("jFasola");
        friend.setRole(Role.USER);
        friend.setFriends(new ArrayList<>());

        RequestFriend requestFriend = new RequestFriend();
        requestFriend.setRequestFriendId(2L);
        requestFriend.setFromUserId(testedUser);
        requestFriend.setToUserId(friend);
        requestFriend.setRequestStatus(RequestStatus.WAITING);

        when(userRepository.findById(1L)).thenReturn(Optional.of(testedUser));
        when(friendRequestRepository.getRequestFriendByRequestFriendId(10L)).thenReturn(Optional.of(requestFriend));
       assertEquals(requestFriend, friendRequestService.updateStatus(10L, RequestStatus.ACCEPTED));
        assertEquals(List.of(friend), userRepository.findById(1L).get().getFriends());

    }

    @Test
    void updateStatus_RequestIsRejected_ShouldReturnRequestFriendAndRemoveIt() {
        RequestFriend requestFriend = new RequestFriend();
        requestFriend.setRequestFriendId(2L);
        requestFriend.setRequestStatus(RequestStatus.WAITING);
        when(friendRequestRepository.getRequestFriendByRequestFriendId(1L)).thenReturn(Optional.of(requestFriend));

        assertEquals(requestFriend, friendRequestService.updateStatus(1L, RequestStatus.REJECTED));
    }

    @Test
    void removeFriendRequestById() {
    }






}