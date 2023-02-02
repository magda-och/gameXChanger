package com.gt.gamexchanger.mapper;

import com.gt.gamexchanger.dto.UserDto;
import com.gt.gamexchanger.model.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserDtoMapperTest {

    UserDtoMapper userDtoMapper;


    @Test
    void toDto() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Iwona");
        user.setLastName("Kula");
        user.setPassword("iwona1");
        user.setEmail("iwona@com");

        userDtoMapper = new UserDtoMapper();
        UserDto userDto = userDtoMapper.toDto(user);

        assertThat(userDto.getId()).isEqualTo(1L);
        assertThat(userDto.getFirstName()).isEqualTo("Iwona");
        assertThat(userDto.getLastName()).isEqualTo("Kula");
        assertThat(userDto.getPassword()).isEqualTo("iwona1");
        assertThat(userDto.getEmail()).isEqualTo("iwona@com");

    }
    @Test
    void toDtoIfFriendIsNull() {
        userDtoMapper = new UserDtoMapper();
        UserDto userDto = userDtoMapper.toDto(null);

        assertNull(userDto);

    }

//    @Test
//    void toDomainObject() {
//        userDtoMapper = new UserDtoMapper();
//        UserDto userDto = new UserDto();
//        userDto.setId(1L);
//        userDto.setFirstName("Iwona");
//        userDto.setLastName("Kula");
//        userDto.setPassword("iwona1");
//        userDto.setEmail("iwona@com");
//
//
//        User user = userDtoMapper.toDomainObject(userDto);
//
//        assertThat(user.getId()).isEqualTo(1);
//        assertThat(user.getFirstName()).isEqualTo("Iwona");
//        assertThat(user.getLastName()).isEqualTo("Kula");
//        assertThat(user.getPassword()).isEqualTo("iwona1");
//        assertThat(user.getEmail()).isEqualTo("iwona@com");
//    }
}