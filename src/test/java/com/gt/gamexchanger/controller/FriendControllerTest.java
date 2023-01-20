package com.gt.gamexchanger.controller;

import com.gt.gamexchanger.dto.FriendDto;

import com.gt.gamexchanger.service.FriendService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.api.VerificationData;

import static org.mockito.Mockito.*;

class FriendControllerTest {

    FriendService friendService;
    FriendControllerTest.UnderTest underTest;
    FriendDto friendDto;
    @BeforeEach
    void setUp() {
        friendDto = new FriendDto();
        friendService = mock(FriendService.class);
        underTest = new FriendControllerTest.UnderTest();
    }

    @Test
    void addNewFriend() {
            underTest.addNewFriend(friendDto);
            verify(friendService, times(1)).saveFriend(friendDto);
    }

    @Test
    void getAllFriends() {
        underTest.getAllFriends(friendDto.getFirstUserId());
        verify(friendService, VerificationData::getAllInvocations).getFriends(friendDto.getFirstUserId());
    }

    public class UnderTest extends FriendController {
        public UnderTest() {
            super(friendService);
        }
    }
}