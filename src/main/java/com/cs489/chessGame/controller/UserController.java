package com.cs489.chessGame.controller;

import com.cs489.chessGame.DTO.userDto.UserDto;
import com.cs489.chessGame.DTO.userDto.UserLoginDTO;
import com.cs489.chessGame.DTO.userDto.UserRegisterForm;
import com.cs489.chessGame.DTO.userDto.UserTokenDTO;
import com.cs489.chessGame.service.interfaces.UserService;
import com.cs489.chessGame.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor

public class UserController {
    private UserService userService;

    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@RequestBody @Valid UserRegisterForm userRegisterForm){
        System.out.println(userRegisterForm);
        return userService.create(userRegisterForm);
    }
    @PostMapping("/auth/login")
    public UserTokenDTO login(@RequestBody @Valid UserLoginDTO userLoginDTO){

        return userService.login(userLoginDTO);
    }
    @GetMapping("/user")
    public UserDto get(Principal principal){
        return userService.findUser(principal.getName());
    }

}
