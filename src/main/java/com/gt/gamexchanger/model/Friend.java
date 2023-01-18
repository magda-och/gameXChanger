package com.gt.gamexchanger.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class Friend {

    private Long id;
    private Date createdDate;
    private User firstUser;
    private User secondUser;

}
