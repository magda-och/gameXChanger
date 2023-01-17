package com.gt.gamexchanger.mapper;

import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper implements DtoMapper<UserDto, User> {

    @Override
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(user.getName(), user.getLastName(), user.getEmail(), user.getPassword());
    }

    @Override
    public User toDomainObject(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setGamesShelf(userDto.getGamesShelf());
        return user;
    }
}

