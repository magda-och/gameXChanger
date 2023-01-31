package com.gt.gamexchanger.mapper;

import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper implements DtoMapper<UserDto, User> {

    @Override
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto =  new UserDto(user.getName(), user.getLastName(), user.getEmail(), user.getPassword());
        userDto.setId(user.getId());

        return userDto;
    }

    @Override
    public User toDomainObject(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
//        user.setGamesShelf(userDto.getGamesShelf());
        return user;
    }
}

