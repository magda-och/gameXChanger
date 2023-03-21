package com.gt.gamexchanger.service;

import com.gt.gamexchanger.dto.GameDto;
import com.gt.gamexchanger.enums.GameStatus;
import com.gt.gamexchanger.exception.NoExistingUser;
import com.gt.gamexchanger.exception.NoGameExists;
import com.gt.gamexchanger.mapper.DtoMapper;
import com.gt.gamexchanger.mapper.GameDtoMapper;
import com.gt.gamexchanger.model.Game;
import com.gt.gamexchanger.model.User;
import com.gt.gamexchanger.repository.GameRepository;
import com.gt.gamexchanger.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final DtoMapper<GameDto, Game> gameDtoMapper;
    private final UserRepository userRepository;

    public GameService(GameRepository gameRepository, GameDtoMapper gameDtoMapper, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.gameDtoMapper = gameDtoMapper;
        this.userRepository = userRepository;
    }

    public GameDto addGame(Long ownerId, GameDto gameDto) {
        Game game = gameDtoMapper.toDomainObject(gameDto);
        if (userRepository.findById(ownerId).isPresent()) {
            User owner = userRepository.findById(ownerId).get();
            game.setOwner(owner);
            game.setActualUser(owner);
            game.setGameStatus(GameStatus.AVAILABLE);
            gameRepository.save(game);
            return gameDtoMapper.toDto(game);
        } else {
            throw new NoExistingUser();
        }
    }

    public List<GameDto> getAllGames() {
        return gameRepository.findAll().stream()
                .sorted(Comparator.comparing(Game::getGameStatus))
                .map(gameDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<GameDto> getGamesByName(String name) {
        return gameRepository.findAllByName(name).stream()
                .sorted(Comparator.comparing(Game::getGameStatus))
                .map(gameDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public boolean deleteGame(Long id) {
        if (getGameById(id).isPresent()) {
            gameRepository.deleteById(id);
        } else {
            throw new NoGameExists();
        }
        return false;
    }

    public void updateGame(Long gameId, GameStatus gameStatus, Long actualUserId) {
        Optional<Game> gameToUpdate = getGameById(gameId);
        System.out.println(gameToUpdate.get().getId());
        if (gameToUpdate.isPresent()) {
            gameToUpdate.get().setGameStatus(gameStatus);
            Optional<User> newActualUser=userRepository.findById(actualUserId);
            gameToUpdate.get().setActualUser(newActualUser.get());
            gameRepository.save(gameToUpdate.get());
        } else {
            throw new NoGameExists();
        }
    }

    private Game updateGameFields(Game game, GameDto gameDto) {
        if (gameDto.getName() != null) {
            game.setName(gameDto.getName());
        }
        if (gameDto.getGameStatus() != null) {
            game.setGameStatus(gameDto.getGameStatus());
        }
        if (gameDto.getActualUserDto() != null) {
            game.setActualUser(game.getActualUser());
        }
        if (gameDto.getVisibility() != null) {
            game.setVisibility(gameDto.getVisibility());
        }
        return game;
    }

    public List<GameDto> getAllMyGames(Long userId) {
        return gameRepository.findAllByOwnerId(userId).stream()
                .map(gameDtoMapper::toDto)
                .toList();
    }

    public List<GameDto> getAllBorrowedGame(Long userId) {
        return gameRepository.getBorrowed(userId).stream()
                .map(gameDtoMapper::toDto)
                .toList();
    }

    public List<GameDto> getByContainingName(String name) {
        return gameRepository.findAllByNameContaining(name).stream()
                .sorted(Comparator.comparing(Game::getGameStatus))
                .map(gameDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public void borrowGame(Long gameId, Long userId) {
        Optional<Game> gameOP = gameRepository.findById(gameId);
        Optional<User> userOP = userRepository.findById(userId);
        User user;
        Game game;
        if (gameOP.isEmpty()) {
            throw new NoGameExists();
        } else if (userOP.isEmpty()) {
            throw new NoExistingUser();
        } else {
            game = gameOP.get();
            user = userOP.get();
            game.setActualUser(user);
            game.setGameStatus(GameStatus.LENT);
            List<Game> games = user.getBorrowedGames();
            games.add(game);
            user.setBorrowedGames(games);
            userRepository.save(user);
            gameRepository.save(game);
        }
    }

    public void giveBackGame(Long gameId, Long userId) {
        Optional<Game> gameOP = gameRepository.findById(gameId);
        Optional<User> userOP = userRepository.findById(userId);
        User user;
        Game game;
        if (gameOP.isEmpty()) {
            throw new NoGameExists();
        } else if (userOP.isEmpty()) {
            throw new NoExistingUser();
        } else {
            game = gameOP.get();
            user = userOP.get();
            game.setActualUser(game.getOwner());
            game.setGameStatus(GameStatus.AVAILABLE);
            List<Game> games = user.getBorrowedGames();
            games.remove(game);
            user.setBorrowedGames(games);
            userRepository.save(user);
            gameRepository.save(game);
        }
    }

    public List<GameDto> getMyFriendsGames(Long userId){
        List<User> friends = userRepository.findUserFriends(userId);
        List<User> friendsWithoutMe=friends.stream().filter(user -> !user.getId().equals(userId)).collect(Collectors.toList());
        List<GameDto> friendsGames = new ArrayList<>();

        for (User friend : friendsWithoutMe){
            Long id=friend.getId();
            List<GameDto> gameList=getAllMyGames(id);
            List<GameDto> onlyAvailableGameList=gameList
                    .stream()
                    .filter(game -> game.getGameStatus().equals(GameStatus.AVAILABLE))
                    .collect(Collectors.toList());

            friendsGames.addAll(onlyAvailableGameList);
        }
        return  friendsGames;
    }
}