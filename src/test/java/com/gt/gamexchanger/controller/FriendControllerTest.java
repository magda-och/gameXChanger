package com.gt.gamexchanger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gt.gamexchanger.model.Friend;
import com.gt.gamexchanger.repository.FriendRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
//@WebMvcTest(FriendController.class)
class FriendControllerTest {

  /*  @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    FriendRepository friendRepository;*/


    @Test
    void addNewFriend() {
    }

    @Test
    void getAllFriends() {
    }
}