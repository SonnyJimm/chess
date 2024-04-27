package com.cs489.chessGame.service.implementations;

import com.cs489.chessGame.DTO.userDto.UserDto;
import com.cs489.chessGame.DTO.userDto.UserLoginDTO;
import com.cs489.chessGame.DTO.userDto.UserRegisterForm;
import com.cs489.chessGame.DTO.userDto.UserTokenDTO;
import com.cs489.chessGame.error.CustomException;
import com.cs489.chessGame.error.Errors;
import com.cs489.chessGame.models.User;
import com.cs489.chessGame.repo.UserRepository;
import com.cs489.chessGame.service.interfaces.UserService;
import com.cs489.chessGame.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public UserDto findUser(String username) {
        var user = findUserByUsername(username);
        user.orElseThrow(()->{throw new CustomException(Errors.NOT_FOUND);});

        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto create(UserRegisterForm userRegisterForm) {
        if(userRepository.findUserByUsername(userRegisterForm.getUsername()).isPresent()){
            throw new CustomException(Errors.DUPLICATE_USERNAME);
        }
        var user = new User(userRegisterForm.getUsername(),passwordEncoder.encode(userRegisterForm.getPassword()),0);
        var newUser = userRepository.save(user);
        return modelMapper.map(newUser,UserDto.class);
    }

    @Override
    public UserTokenDTO login(UserLoginDTO userLoginDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(),userLoginDTO.getPassword()));
        var user = userRepository.findUserByUsername(userLoginDTO.getUsername()).orElseThrow(()->{throw new CustomException(Errors.NOT_FOUND);});
        var jwtToken = jwtUtil.generateToken(userLoginDTO.getUsername());
        return new UserTokenDTO(jwtToken);
    }
}
