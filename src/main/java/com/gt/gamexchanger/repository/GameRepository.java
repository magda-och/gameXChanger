package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.Game;
import com.gt.gamexchanger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    public void addGame(Game game);

    public void updateGame(Game game);

    public Game deleteGame(Long id);

    public List<Game> getAllGames();

    public List<Game> findAllByOwnerId(Long id);

    public List<Game> getMyBorrowedGames(Long idActualUser);

    public List<Game> getGamesByName(String name);

    public Game getGameById(Long id);

}
