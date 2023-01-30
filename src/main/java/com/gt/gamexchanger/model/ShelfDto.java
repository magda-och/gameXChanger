package com.gt.gamexchanger.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
public class ShelfDto {

    //@OneToOne
    //User user;
    private long shelfId;
    private long ownerId;


    public ShelfDto(long ownerId) {
        this.ownerId = ownerId;
    }
}
