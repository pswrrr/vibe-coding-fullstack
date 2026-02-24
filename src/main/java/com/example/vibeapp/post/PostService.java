package com.example.vibeapp.post;

import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Collections;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;

    public PostService(PostRepository postRepository, PostTagRepository postTagRepository) {
        this.postRepository = postRepository;
        this.postTagRepository = postTagRepository;
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
        postRepository.update(post);

        // 태그 조회
        List<PostTag> tags = postTagRepository.findByPostNo(no);
        String tagsString = tags.stream()
                .map(PostTag::getTagName)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        return PostResponseDto.from(post, tagsString);
    }

    @Transactional
    public void addPost(PostCreateDto createDto) {
        Post post = createDto.toEntity();
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setViews(0);

        postRepository.save(post);

        // 태그 저장
        saveTags(post.getNo(), createDto.tags());
    }

    @Transactional
    public void updatePost(Long no, PostUpdateDto updateDto) {
        Post post = postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. No: " + no));

        post.setTitle(updateDto.title());
        post.setContent(updateDto.content());
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.update(post);

        // 태그 업데이트 (기존 삭제 후 신규 저장)
        postTagRepository.deleteByPostNo(no);
        saveTags(no, updateDto.tags());
    }

    private void saveTags(Long postNo, String tagsString) {
        if (tagsString == null || tagsString.isBlank()) {
            return;
        }

        String[] tags = tagsString.split(",");
        for (String tagName : tags) {
            String trimmedTag = tagName.trim();
            if (!trimmedTag.isEmpty()) {
                PostTag postTag = new PostTag();
                postTag.setPostNo(postNo);
                postTag.setTagName(trimmedTag);
                postTagRepository.save(postTag);
            }
        }
    }

    public void deletePost(Long no) {
        postRepository.deleteByNo(no);
    }
}
