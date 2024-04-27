package com.cs489.chessGame.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomError {
    @JsonIgnore
    private HttpStatus status;
    private String message;
    private Map<String, String> details;

    public CustomError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}