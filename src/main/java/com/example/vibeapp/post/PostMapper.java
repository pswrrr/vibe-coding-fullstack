package com.example.vibeapp.post;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    List<Post> findAll();

    Optional<Post> findByNo(Long no);

    void save(Post post);

    void update(Post post);

    void deleteByNo(Long no);
}
