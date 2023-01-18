package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserRepositoryImp implements UserRepository {
    private final Map<Long, User> inMemoryUsers = new HashMap<>();

    @Override
    public void addUser(User user){
        user.setId((long) (inMemoryUsers.values().size() + 1));
        inMemoryUsers.put(user.getId(), user);
    }

    @Override
    public List<User> getAllUsers(){
        return new ArrayList<>(inMemoryUsers.values());
    }

    @Override
    public List<User> findUserByFullName(String name, String lastName){
        return inMemoryUsers.values().stream()
                .filter(user -> user.getName().equalsIgnoreCase(name) && user.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findUserByLastName(String lastName){
        return inMemoryUsers.values().stream()
                .filter(user -> user.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }
    @Override
    public List<User> findUserByFirstName(String firstName){
        return inMemoryUsers.values().stream()
                .filter(user -> user.getName().equalsIgnoreCase(firstName))
                .collect(Collectors.toList());
    }



    @Override
    public void deleteUser(Long id) {
        inMemoryUsers.remove(id);
    }

//    @Override
//    public deleteUser(Long id){
//
//    }
}
