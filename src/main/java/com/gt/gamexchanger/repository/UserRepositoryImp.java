package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserRepositoryImp implements UserRepository {
    private final Map<Long, User> inMemoryUsers = new HashMap<>();

    @Override
    public void addUser(User user){
        inMemoryUsers.put(user.getId(), user);
    }

    @Override
    public List<User> getAllUsers(){
        return new ArrayList<>(inMemoryUsers.values());
    }

    @Override
    public User getUserById(Long id) {
        return inMemoryUsers.get(id);
    }
}
