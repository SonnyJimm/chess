package com.cs489.chessGame.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cs489.chessGame.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer>{
    public Optional<User> findUserByUsername(String username);
}
