package com.myrobin.postr.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDto {
    private String post;
    private List<String> comments;
}