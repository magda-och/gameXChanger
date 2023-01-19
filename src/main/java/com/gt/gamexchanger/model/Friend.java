package com.gt.gamexchanger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Friend {

    //private Date createdDate;
    private Long firstUserId;
    private Long secondUserId;

}
