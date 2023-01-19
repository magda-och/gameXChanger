package com.gt.gamexchanger.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class Friend {

    //private Date createdDate;
    private Long firstUserId;
    private Long secondUserId;

}
