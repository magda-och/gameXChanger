package com.gt.gamexchanger.repository;

import com.gt.gamexchanger.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {


    public List<Game> findAllByOwnerId(Long id);

    @Query("select g from games g where g.actualUser.id = :userId AND g.owner.id <> :userId")
    public List<Game> getBorrowed(Long userId);

    public List<Game> findAllByName(String name);

    public List<Game> findAllByNameContaining(String name);

}
