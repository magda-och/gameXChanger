package com.gt.gamexchanger.mapper;

import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper implements DtoMapper<UserDto, User> {

    @Override
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getPassword(), user.getCity(), user.getPhoneNumber(), user.getRole());
    }

    @Override
    public User toDomainObject(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setCity(userDto.getCity());
        user.setPhoneNumber(userDto.getPhoneNumber());

        return user;
    }
}

