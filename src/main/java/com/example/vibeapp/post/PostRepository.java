package com.example.vibeapp.post;

import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private final List<Post> posts = new ArrayList<>();
    private Long nextNo = 11L;

    public PostRepository() {
        // Initialize with 10 dummy data items
        for (long i = 1; i <= 10; i++) {
            posts.add(new Post(
                    i,
                    "바이브 코딩 게시글 제목 " + i,
                    "이것은 " + i + "번째 게시글의 상세 내용입니다.",
                    LocalDateTime.now().minusDays(10 - i),
                    (int) (Math.random() * 100)));
        }
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts);
    }

    public java.util.Optional<Post> findByNo(Long no) {
        return posts.stream()
                .filter(post -> post.getNo().equals(no))
                .findFirst();
    }

    public void save(Post post) {
        post.setNo(nextNo++);
        posts.add(post);
    }

    public void deleteByNo(Long no) {
        posts.removeIf(post -> post.getNo().equals(no));
    }
}
