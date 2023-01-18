package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.Game;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface GameRepository {

    public void addGame(Game game);

    public void updateGame(Game game);

    public void deleteGame(Game game);

    public void getGameDetails(Game game);

    public List<Game> getAllGames();

    public List<Game> getMyGames();

    public List<Game> getMyBorrowedGames();

    public List<Game> getGamesByName(String name);

    public Game getGameById(Long id);


}
