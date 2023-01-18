package com.gt.gamexchanger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendDto {

    //private Date createdDate;
    Long firstUserId;
    Long secondUserId;

}
