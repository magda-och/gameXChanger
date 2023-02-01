//package com.gt.gamexchanger.repository;
//
//import com.gt.gamexchanger.model.Game;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//import java.util.stream.Collectors;
//@Component
//public class GameRepositoryImp implements GameRepository {
//
//    private final Map<Long, Game> games = new HashMap<>();
//
//    public Map<Long, Game> getGames() {
//        return games;
//    }
//
//
//    @Override
//    public void addGame(Game game) {
//        game.setId((long) (games.values().size() + 1));
//        games.put(game.getId(), game);
//    }
//
//    @Override
//    public List<Game> getGamesByName(String name) {
//        return games.values().stream().filter(game -> game.getName()
//                .contains(name)).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Game> getAllGames() {
//        return new ArrayList<>(games.values());
//    }
//
//    @Override
//    public Game getGameById(Long id) {
//        return games.get(id);
//    }
//
//    @Override
//    public void updateGame(Game game) {
//
//    }
//
//    @Override
//    public Game deleteGame(Long id) {
//        return games.remove(id);
//    }
//
//    @Override
//    public List<Game> getMyGames(Long id) {
//      return games.values().stream()
//                .filter(game -> Objects.equals(game.getOwnerId(), id))
//                .collect(Collectors.toList());
//    }
//    @Override
//    public List<Game> getMyBorrowedGames(Long idActualUser) {
//        return games.values().stream()
//                .filter(g -> g.getActualUserId().equals(idActualUser) && !( g.getId().equals(idActualUser)))
//                .collect(Collectors.toList());
//    }
//}
