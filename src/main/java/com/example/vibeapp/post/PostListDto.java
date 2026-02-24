package com.example.vibeapp.post;

import java.time.LocalDateTime;

public record PostListDto(
        Long no,
        String title,
        LocalDateTime createdAt,
        Integer views) {
    public static PostListDto from(Post entity) {
        return new PostListDto(
                entity.getNo(),
                entity.getTitle(),
                entity.getCreatedAt(),
                entity.getViews());
    }
}
