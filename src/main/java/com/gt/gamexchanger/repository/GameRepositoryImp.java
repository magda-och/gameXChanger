package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.Game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameRepositoryImp implements GameRepository {

    private final Map<Long, Game> games = new HashMap<>();


    @Override
    public void addGame(Game game) {
        game.setId((long) (games.values().size() +1));
        games.put(game.getId(), game);
    }
    @Override
    public Game getGameByName(String name) {
        return null;
    }

    @Override
    public Game getGameById(Long id) {
        return null;
    }
    @Override
    public void updateGame(Game game) {

    }

    @Override
    public void deleteGame(Game game) {

    }

    @Override
    public void getGameDetails(Game game) {

    }

    @Override
    public List<Game> getAllGames() {
        return null;
    }

    @Override
    public List<Game> getMyGames() {
        return null;
    }

    @Override
    public List<Game> getMyBorrowedGames() {
        return null;
    }
}
