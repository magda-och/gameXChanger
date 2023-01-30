package com.gt.gamexchanger.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
public class Shelf {
    private long shelfId;
    private long ownerId;
// wprowadzić dwie listy gier

    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "shelf")
    //  private Set<Game> games;
    public Shelf(long ownerId) {
        this.ownerId = ownerId;
    }
}
