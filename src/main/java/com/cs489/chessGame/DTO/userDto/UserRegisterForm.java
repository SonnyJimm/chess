package com.cs489.chessGame.DTO.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterForm {
    @NotNull(message = "username cant be empty")
    @NotEmpty(message = "username cant be empty")
    @Size(min = 4, max = 15, message = "Username too short")
    private String username;
    @NotNull(message = "username cant be empty")
    @NotEmpty(message = "password cant be empty")
    @Size(min = 8, max = 15, message = "Password too short")
    private String password;    
}
