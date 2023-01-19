package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.Friend;
import com.gt.gamexchanger.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class FriendRepositoryImpTest {
    public static List<User> users;

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


        users = new ArrayList<>();
        User user1 = new User();
        long i = 1;
        user1.setId(i);
        user1.setName("Jane");
        user1.setLastName("Plain");
        user1.setEmail("pj@com");
        user1.setPassword("pj123");

        User user2 = new User();
        long j = 2;
        user2.setId(j);
        user2.setName("Bill");
        user2.setLastName("Kill");
        user2.setEmail("killbill@com");
        user2.setPassword("kb123");

        User user3 = new User();
        long k = 1;
        user3.setId(k);
        user3.setName("John");
        user3.setLastName("Bean");
        user3.setEmail("mrbean@com");
        user3.setPassword("SpringBean2");

        users.add(user1);
        users.add(user2);
        users.add(user3);
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