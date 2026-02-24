package com.example.vibeapp.post;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    private final PostMapper postMapper;

    public PostRepository(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    public List<Post> findAll() {
        return postMapper.findAll();
    }

    public Optional<Post> findByNo(Long no) {
        return postMapper.findByNo(no);
    }

    public void save(Post post) {
        postMapper.save(post);
    }

    public void deleteByNo(Long no) {
        postMapper.deleteByNo(no);
    }

    public void update(Post post) {
        postMapper.update(post);
    }
}
