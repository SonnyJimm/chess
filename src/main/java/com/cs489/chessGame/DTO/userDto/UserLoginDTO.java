package com.cs489.chessGame.DTO.userDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginDTO {
    @NotEmpty
    @Size(min = 4, max = 15, message = "Username too short")
    private String username;
    @NotEmpty
    @Size(min = 8, max = 15, message = "Password too short")
    private String password;
}
