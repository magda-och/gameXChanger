package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.Game;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface GameRepository {

    public void addGame(Game game);

    public void updateGame(Game game);

    public Game deleteGame(Long id);

    public List<Game> getAllGames();

    public List<Game> getMyGames(Long id);

    public List<Game> getMyBorrowedGames(Long idActualUser);

    public List<Game> getGamesByName(String name);

    public Game getGameById(Long id);

}
