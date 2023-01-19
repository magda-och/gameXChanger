package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.Friend;
import com.gt.gamexchanger.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class FriendRepositoryImpTest {

    UserRepository userRepository = mock(UserRepository.class);
    private User user100;

    private User user200;

    @BeforeEach
    void init(){
        user100 = new User();
        user100.setId(100L);
        user100.setName("Jane100");
        user100.setLastName("Plain100");
        user100.setEmail("pj100@com");
        user100.setPassword("pj100");

        user200 = new User();
        user200.setId(200L);
        user200.setName("John200");
        user200.setLastName("Plain200");
        user200.setEmail("pj200@com");
        user200.setPassword("JK200");

    }

    @Test
    void saveFriend() {

        FriendRepositoryImp friendRepository = new FriendRepositoryImp(userRepository);
        when(userRepository.getUserById(100L)).thenReturn(Optional.of(user100));
        when(userRepository.getUserById(200L)).thenReturn(Optional.of(user200));

        Friend friend = new Friend(100L, 200L);

        friendRepository.saveFriend(friend);

        List<User> friends = friendRepository.getFriends(100L);
        assertNotNull(friends);
        assertEquals(1, friends.size());
        assertEquals(user200, friends.get(0));
    }

    @Test
    void getFriends() {
    }

    @Test
    void removeFriendById() {
    }
}