package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRepositoryImpTest {

    UserRepositoryImp userRepositoryImp = new UserRepositoryImp();
    User user1;
    User user2;

    @BeforeEach
    void setUp() {

        user1 = new User("Jan", "Kowalski", "janek@gmail.com", "123");
        user2 = new User("Maja", "Wolska", "majka@gmail.com", "majka");
    }

    @Test
    void addUser_addCertainUsers_usersAdded() {
        //when
        userRepositoryImp.addUser(user1);
        userRepositoryImp.addUser(user2);
        // then
        assertEquals(user1, userRepositoryImp.getAllUsers().get(0));
        assertEquals(user2, userRepositoryImp.getAllUsers().get(1));
        assertEquals(2, userRepositoryImp.getAllUsers().size());
    }


    @Test
    void getAllUsers_beforeAddingUsers_emptyList() {
        assertEquals(0, userRepositoryImp.getAllUsers().size());
    }

    @Test
    void getAllUsers_afterAddUsers_listCreatedProperly() {
        List<User> testingList = new ArrayList<>();
        testingList.add(user1);
        testingList.add(user2);

        userRepositoryImp.addUser(user1);
        userRepositoryImp.addUser(user2);

        assertEquals(testingList, userRepositoryImp.getAllUsers());
    }

    @Test
    void findUserByName_userAdded_returnUserByHisFirstName() {
        userRepositoryImp.addUser(user1);

        assertEquals(user1, userRepositoryImp.findUserByName("Jan").get(0));
    }

    @Test
    void findUserByName_userAdded_returnUserByHisLastName() {
        userRepositoryImp.addUser(user1);
        User testingUser = userRepositoryImp.findUserByName("Kowalski").get(0);

        assertEquals(user1, testingUser);
    }

    @Test
    void findUserByName_userAdded_returnUserByHisFullName() {
        userRepositoryImp.addUser(user1);

        User testingUser = userRepositoryImp.findUserByName("Jan Kowalski").get(0);

        assertEquals(user1, testingUser);
    }

    @Test
    void deleteUser_correctId_userRemovedFromTheList() {
        userRepositoryImp.addUser(user1);

        userRepositoryImp.deleteUser(user1.getId());

        assertEquals(0, userRepositoryImp.getAllUsers().size());
    }

    @Test
    void deleteUser_nonexistentId_userNotRemoved() {
        userRepositoryImp.addUser(user1);

        userRepositoryImp.deleteUser(2L);

        assertEquals(1, userRepositoryImp.getAllUsers().size());
    }

    @Test
    void getUserById_correctId_userFounded() {
        userRepositoryImp.addUser(user1);

        Optional<User> testingUser = userRepositoryImp.getUserById(1L);

        assertEquals(user1, testingUser.get());
    }

    @Test
    void getUserById_nonexistentId_userNotFound() {
        userRepositoryImp.addUser(user1);

        Optional<User> testingUser = userRepositoryImp.getUserById(2L);

        assertTrue(testingUser.isEmpty());
    }
}
