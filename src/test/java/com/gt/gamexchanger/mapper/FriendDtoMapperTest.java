package com.gt.gamexchanger.mapper;

import com.gt.gamexchanger.dto.FriendDto;
import com.gt.gamexchanger.model.Friend;
import com.gt.gamexchanger.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class FriendDtoMapperTest {

    private FriendDtoMapper friendDtoMapper;

    private FriendDto friendDto;
    private User user2;
    private User user1;

    private Friend friend;
    private Date now;

    @BeforeEach
    void setUp() {
        friendDto = new FriendDto();
        user1 = new User();
        user1.setId(100L);
        user1.setFirstName("Jane");
        user1.setLastName("Plain");
        user1.setEmail("pj100@com");
        user1.setPassword("pj100");

        user2 = new User();
        user2.setId(2L);
        user2.setFirstName("John");
        user2.setLastName("Bean");
        user2.setEmail("mrBean@com");
        user2.setPassword("JB200");
        friendDtoMapper = new FriendDtoMapper();
        now = new Date();
        friend = new Friend(100L,now, user1, user2);

    }

    @Test
    void toDto() {
        friendDto = friendDtoMapper.toDto(friend);

        assertThat(friendDto.getFirstUser()).isEqualTo(user1);
        assertThat(friendDto.getSecondUser()).isEqualTo(user2);
        assertThat(friendDto.getCreatedDate()).isEqualTo(now);

    }
    @Test
    void toDtoIfFriendIsNull() {
        friendDto = friendDtoMapper.toDto(null);

        assertNull(friendDto);

    }

    @Test
    void toDomainObject() {
        friendDtoMapper = new FriendDtoMapper();
        friendDto = friendDtoMapper.toDto(friend);

        Friend friendToCheck = friendDtoMapper.toDomainObject(friendDto);

        assertThat(friendToCheck.getFirstUser()).isEqualTo(user1);
        assertThat(friendToCheck.getSecondUser()).isEqualTo(user2);
    }
}
