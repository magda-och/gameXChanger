package com.gt.gamexchanger.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Data
public class Shelf {
    private long shelfId;
    private long ownerId;

    public Shelf(long ownerId) {
        this.ownerId = ownerId;
    }
}
