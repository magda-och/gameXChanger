package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.service.FriendService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.api.VerificationData;

import static org.mockito.Mockito.*;

class FriendControllerTest {

    FriendService friendService;
    FriendControllerTest.UnderTest underTest;
    UserDto currentUserDto;
    Long friendId; // prywatne
    @BeforeEach
    void setUp() {
        currentUserDto = new UserDto(); // mockowac zaleznosci!!!!!!!!!!!
        friendId = 2L; // do stalej
        friendService = mock(FriendService.class);
        underTest = new FriendControllerTest.UnderTest();
        // chyba ze model tworzy prymitiwny, ale reszte mockowac
    }

    @Test
    void addNewFriend() {
            underTest.addNewFriend(currentUserDto, friendId);
            verify(friendService, times(1)).saveFriend(currentUserDto, friendId);
    }

    @Test
    void getAllFriends() {
        underTest.getFriends(currentUserDto.getId());
        verify(friendService, VerificationData::getAllInvocations).getFriends(currentUserDto.getId());
    }

    public class UnderTest extends FriendController {
        public UnderTest() {
            super(friendService);
        }
    }
}
