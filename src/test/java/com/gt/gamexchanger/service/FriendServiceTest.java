package com.gt.gamexchanger.service;

import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.repository.FriendRepository;
import com.gt.gamexchanger.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class FriendServiceTest {

    @Mock
    private FriendRepository friendRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FriendService friendService;

    private User user1;

    private User user2;


    @BeforeEach
    void setUp(){
        user1 = new User();
        user1.setId(100L);
        user1.setFirstName("Jane");
        user1.setLastName("Plain");
        user1.setEmail("pj100@com");
        user1.setPassword("pj100");

        user2 = new User();
        user2.setId(200L);
        user2.setFirstName("John");
        user2.setLastName("Bean");
        user2.setEmail("mrBean@com");
        user2.setPassword("JB200");
    }
/*
    @Test
    void givenUserObject_whenSaveUserAsFriend_thenReturnFriendObject() {
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());

        given(employeeRepository.save(employee)).willReturn(employee);
        given(userRepository.findUserById(user2.getId()))
                .willReturn(Optional.empty());

        given(friendRepository.save(user2, user1.getId())).willReturn()
    }

    @Test
    void getFriends() {
    }

  */
}