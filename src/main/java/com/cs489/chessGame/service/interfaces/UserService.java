package com.cs489.chessGame.service.interfaces;

import com.cs489.chessGame.DTO.userDto.UserDto;
import com.cs489.chessGame.DTO.userDto.UserLoginDTO;
import com.cs489.chessGame.DTO.userDto.UserRegisterForm;
import com.cs489.chessGame.DTO.userDto.UserTokenDTO;
import com.cs489.chessGame.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {
    public Optional<User> findUserByUsername(String username);

    public UserDto findUser(String username);
    public UserDto create(UserRegisterForm userRegisterForm);
    public UserTokenDTO login(UserLoginDTO userLoginDTO);
}