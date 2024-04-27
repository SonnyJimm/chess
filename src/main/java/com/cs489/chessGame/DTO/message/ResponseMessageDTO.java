package com.cs489.chessGame.DTO.message;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseMessageDTO {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
