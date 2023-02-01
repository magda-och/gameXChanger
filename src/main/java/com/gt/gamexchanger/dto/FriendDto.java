package com.gt.gamexchanger.dto;

import com.gt.gamexchanger.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendDto {

    private Date createdDate;

    private User firstUserId;

    private User secondUserId;

}
