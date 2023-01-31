//package com.gt.gamexchanger.repository;
//
//import com.gt.gamexchanger.model.Friend;
//import com.gt.gamexchanger.model.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//
//class FriendRepositoryImpTest {
//
//
//    UserRepository userRepository = mock(UserRepository.class);
//    private User user100;
//
//    private User user200;
//    private User user300;
//
//    FriendRepositoryImp friendRepository;
//
//    @BeforeEach
//    void init(){
//
//        user100 = new User();
//        user100.setId(100L);
//        user100.setName("Jane");
//        user100.setLastName("Plain");
//        user100.setEmail("pj100@com");
//        user100.setPassword("pj100");
//
//        user200 = new User();
//        user200.setId(200L);
//        user200.setName("John");
//        user200.setLastName("Bean");
//        user200.setEmail("mrBean@com");
//        user200.setPassword("JB200");
//
//        user300 = new User();
//        user300.setId(300L);
//        user300.setName("Kill");
//        user300.setLastName("Bill");
//        user300.setEmail("killBill@com");
//        user300.setPassword("kb300");
//
//
//        friendRepository = new FriendRepositoryImp(userRepository);
//
//    }
//
//    @Test
//    void saveFriendSuccess() {
//
//        when(userRepository.getUserById(100L)).thenReturn(Optional.of(user100));
//        when(userRepository.getUserById(200L)).thenReturn(Optional.of(user200));
//
//        Friend friend = new Friend(100L, 200L);
//
//        friendRepository.saveFriend(friend);
//
//        List<User> friendsU100 = friendRepository.getFriends(100L);
//        List<User> friendsU200 = friendRepository.getFriends(200L);
//        assertNotNull(friendsU100);
//        assertEquals(1, friendsU100.size());
//        assertEquals(user200, friendsU100.get(0));
//        assertEquals(user100, friendsU200.get(0));
//    }
//
//    @Test
//    void getFriendsWhenBadId() {
//
//        assertThrows(RuntimeException.class, () -> friendRepository.getFriends(500L));
//
//    }
//    @Test
//    void getFriendsWhenGoodId() {
//        when(userRepository.getUserById(100L)).thenReturn(Optional.of(user100));
//        when(userRepository.getUserById(200L)).thenReturn(Optional.of(user200));
//        when(userRepository.getUserById(300L)).thenReturn(Optional.of(user300));
//        List<User> expectedList = new ArrayList<>();
//        expectedList.add(user100);
//        expectedList.add(user200);
//
//        Friend friend1 = new Friend(300L, 100L);
//        Friend friend2 = new Friend(300L, 200L);
//        friendRepository.saveFriend(friend1);
//        friendRepository.saveFriend(friend2);
//
//        assertEquals(expectedList, friendRepository.getFriends(300L) );
//    }
//
//    @Test
//    void removeFriend() {
//
//        when(userRepository.getUserById(100L)).thenReturn(Optional.of(user100));
//        when(userRepository.getUserById(200L)).thenReturn(Optional.of(user200));
//
//        Friend friend1 = new Friend(100L, 200L);
//
//        friendRepository.saveFriend(friend1);
//
//        assertTrue(friendRepository.removeFriend(friend1));
//
//    }
//}