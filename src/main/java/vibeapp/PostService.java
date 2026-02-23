package vibeapp;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostByNo(Long no) {
        Post post = postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. No: " + no));

        // 조회수 증가
        post.setViews(post.getViews() + 1);
        post.setUpdatedAt(java.time.LocalDateTime.now());

        return post;
    }

    public void addPost(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedAt(java.time.LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setViews(0);

        postRepository.save(post);
    }

    public void updatePost(Long no, String title, String content) {
        Post post = postRepository.findByNo(no)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. No: " + no));

        post.setTitle(title);
        post.setContent(content);
        post.setUpdatedAt(java.time.LocalDateTime.now());
    }
}
