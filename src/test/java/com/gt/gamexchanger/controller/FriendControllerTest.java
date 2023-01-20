package com.gt.gamexchanger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gt.gamexchanger.dto.FriendDto;
import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.model.Friend;
import com.gt.gamexchanger.repository.FriendRepository;
import com.gt.gamexchanger.service.FriendService;
import com.gt.gamexchanger.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@WebMvcTest(FriendController.class)
class FriendControllerTest {

  /*  @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    FriendRepository friendRepository;*/
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
        verify(friendService, verificationData -> verificationData.getAllInvocations());
    }

    public class UnderTest extends FriendController {
        public UnderTest() {
            super(friendService);
        }
    }
}