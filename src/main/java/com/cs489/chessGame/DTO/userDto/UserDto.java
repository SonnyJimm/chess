package com.cs489.chessGame.DTO.userDto;

import com.cs489.chessGame.DTO.gameDto.GameDTO;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private Integer score;
    private List<GameDTO> games;
}
