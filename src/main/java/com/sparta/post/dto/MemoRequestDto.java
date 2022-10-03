package com.sparta.post.dto;

import lombok.Getter;

@Getter
public class MemoRequestDto {
    private String title;
    private String username;
    private String contents;
    private String password;
}