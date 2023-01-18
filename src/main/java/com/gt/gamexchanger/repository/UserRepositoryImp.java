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
        user.setId(inMemoryUsers.values().size() + 1);
        inMemoryUsers.put(user.getId(), user);
    }

    @Override
    public List<User> getAllUsers(){
        return new ArrayList<>(inMemoryUsers.values());
    }

    @Override
    public List<User> findUserByName(String name, String lastName){
        return inMemoryUsers.values().stream()
                .filter(user -> user.getName().equals(name) && user.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findUserByName(String lastName){
        return inMemoryUsers.values().stream()
                .filter(user -> user.getLastName().equals(lastName))
                .collect(Collectors.toList());


    }

    @Override
    public User getUserById(Long id) {
        return inMemoryUsers.get(id);
    }
}
