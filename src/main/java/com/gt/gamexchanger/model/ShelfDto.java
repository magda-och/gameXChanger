package com.gt.gamexchanger.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ShelfDto {
    private long shelfId;
    private long ownerId;

    public ShelfDto(long ownerId) {
        this.ownerId = ownerId;
    }
}
