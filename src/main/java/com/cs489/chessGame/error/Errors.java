package com.cs489.chessGame.error;

import org.springframework.http.HttpStatus;

public class Errors {
    public static final CustomError INTERNAL_ERROR = new CustomError(HttpStatus.INTERNAL_SERVER_ERROR,
            "Internal server error.");
    public static final CustomError INVALID_VALUE = new CustomError(HttpStatus.BAD_REQUEST,
            "Validation(s) are failed.");
    public static final CustomError DUPLICATE_USERNAME = new CustomError(HttpStatus.BAD_REQUEST,
            "Username taken please choose different name.");
    public static final CustomError NOT_FOUND = new CustomError(HttpStatus.NOT_FOUND,
            "User not found");
    public static final CustomError INVALID_USERNAME_OR_PASSWORD = new CustomError(HttpStatus.UNAUTHORIZED,
            "Invalid credential username or password wrong");
    public static final CustomError INVALID_TOKEN = new CustomError(HttpStatus.BAD_REQUEST,
            "Invalid token");
}
