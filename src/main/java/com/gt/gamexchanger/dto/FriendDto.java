package com.gt.gamexchanger.dto;

import com.gt.gamexchanger.enums.RequestStatus;
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

    private User firstUser;

    private User secondUser;

    private RequestStatus requestStatus;

}
