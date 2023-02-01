package com.gt.gamexchanger.service;

import com.gt.gamexchanger.model.Game;
import com.gt.gamexchanger.repository.GameRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameRepositoryImp implements GameRepository {

    private final Map<Long, Game> games = new HashMap<>();

    public Map<Long, Game> getGames() {
        return games;
    }


    @Override
    public <S extends Game> S save(S entity) {
        entity.setId((long) (games.values().size() + 1));
        games.put(entity.getId(), entity);
        return entity;
    }
    public List<Game> getGamesByName(String name) {
        return games.values().stream().filter(game -> game.getName()
                .contains(name)).collect(Collectors.toList());
    }


    public List<Game> getAllGames() {
        return new ArrayList<>(games.values());
    }
    @Override
    public List<Game> findAll() {
        return new ArrayList<>(games.values());
    }
    @Override
    public <S extends Game> List<S> findAll(Example<S> example) {
        return null;
    }

    public Game getGameById(Long id) {
        return games.get(id);
    }


    public void updateGame(Game game) {

    }
    @Override
    public List<Game> findAllByOwnerId(Long id) {
        return games.values().stream()
                .filter(game -> Objects.equals(game.getOwner(), id))
                .collect(Collectors.toList());
    }

    @Override
    public List<Game> getBorrowed(Long userId) {
        return games.values().stream()
                .filter(g -> g.getActualUser().equals(userId) && !(g.getId().equals(userId)))
                .collect(Collectors.toList());
    }
    @Override
    public void deleteById(Long aLong) {
        games.remove(aLong);
    }
    @Override
    public List<Game> findAllByName(String name) {
        return games.values().stream().filter(game -> game.getName()
                .contains(name)).collect(Collectors.toList());
    }

    @Override
    public List<Game> findAllByNameContaining(String name) {
        return null;
    }
    @Override
    public void flush() {

    }

    @Override
    public <S extends Game> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Game> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Game> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Game getOne(Long aLong) {
        return null;
    }

    @Override
    public Game getById(Long aLong) {
        return null;
    }

    @Override
    public Game getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Game> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }


    @Override
    public <S extends Game> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Game> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Game> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Game> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Game, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }



    @Override
    public <S extends Game> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Game> findById(Long aLong) {
        return Optional.ofNullable(games.get(aLong));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }


    @Override
    public List<Game> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Game entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Game> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Game> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Game> findAll(Pageable pageable) {
        return null;
    }


}
