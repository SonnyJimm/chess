package com.cs489.chessGame.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cs489.chessGame.models.Game;

public interface GameRepository extends JpaRepository<Game,Integer>{

}
