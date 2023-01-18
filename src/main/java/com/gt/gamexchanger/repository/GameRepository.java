package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.Game;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository {

    public void addGame(Game game);

    public void updateGame(Game game);

    public void deleteGame(Game game);

    public void getGameDetails(Game game);

}
