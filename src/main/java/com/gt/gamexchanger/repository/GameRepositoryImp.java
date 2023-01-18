package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.Game;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Component
public class GameRepositoryImp implements GameRepository {

    private final Map<Long, Game> games = new HashMap<>();


    @Override
    public void addGame(Game game) {
        game.setId((long) (games.values().size() + 1));
        games.put(game.getId(), game);
    }

    @Override
    public List<Game> getGamesByName(String name) {
        return games.values().stream().filter(game -> game.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<Game> getAllGames() {
        return new ArrayList<>(games.values());
    }

    @Override
    public Game getGameById(Long id) {
        return games.get(id);
    }

    @Override
    public void updateGame(Game game) {

    }

    @Override
    public void deleteGame(Game game) {
        Game removedGame = games.remove(game.getId());
    }

    @Override
    public void getGameDetails(Game game) {

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
