package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserRepositoryImp implements UserRepository {
    private final Map<Long, User> inMemoryUsers = new HashMap<>();

    @Override
    public void addUser(User user) {
        user.setId((long) (inMemoryUsers.values().size() + 1));
        inMemoryUsers.put(user.getId(), user);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(inMemoryUsers.values());
    }

    public List<User> findUserByFullName(String[] fullName) {
        List<User> userResults1 = inMemoryUsers.values().stream()
                .filter(user -> user.getName().equalsIgnoreCase(fullName[0]) && user.getLastName().equalsIgnoreCase(fullName[1]))
                .collect(Collectors.toList());

        userResults1.addAll(inMemoryUsers.values().stream()
                .filter(user -> user.getName().equalsIgnoreCase(fullName[1]) && user.getLastName().equalsIgnoreCase(fullName[0]))
                .collect(Collectors.toList()));

        return userResults1;


    }

    public List<User> findUserByLastName(String lastName) {
        return inMemoryUsers.values().stream()
                .filter(user -> user.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    public List<User> findUserByFirstName(String firstName) {
        return inMemoryUsers.values().stream()
                .filter(user -> user.getName().equalsIgnoreCase(firstName))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findUserByName(String name) {
        if (name.contains(" ")) {
            String[] fullName = name.split(" ");
            if (fullName.length > 1) {
                return findUserByFullName(fullName);
            }
        }
        List<User> results = findUserByLastName(name.replaceAll(" ", ""));
        results.addAll(findUserByFirstName(name.replaceAll(" ", "")));
        return results;
    }

    @Override
    public void deleteUser(Long id) {
        inMemoryUsers.remove(id);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(inMemoryUsers.get(id));
    }
}