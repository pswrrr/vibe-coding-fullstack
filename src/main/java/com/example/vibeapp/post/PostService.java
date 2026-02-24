package com.example.vibeapp.post;

import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostListDto> getPostsByPage(int page, int size) {
        int offset = (page - 1) * size;
        return postRepository.findAllWithPaging(offset, size).stream()
                .map(PostListDto::from)
                .toList();
    }

    public int getTotalPages(int size) {
        int totalPosts = postRepository.count();
        return (int) Math.ceil((double) totalPosts / size);
    }

    public PostResponseDto getPostByNo(Long no) {
        Post post = postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. No: " + no));

        // 조회수 증가
        post.setViews(post.getViews() + 1);
        post.setUpdatedAt(LocalDateTime.now());

        return PostResponseDto.from(post);
    }

    public void addPost(PostCreateDto createDto) {
        Post post = createDto.toEntity();
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setViews(0);

        postRepository.save(post);
    }

    public void updatePost(Long no, PostUpdateDto updateDto) {
        Post post = postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. No: " + no));

        post.setTitle(updateDto.title());
        post.setContent(updateDto.content());
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.update(post);
    }

    public void deletePost(Long no) {
        postRepository.deleteByNo(no);
    }
}
