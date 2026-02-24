package com.example.vibeapp.post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PostTagRepository {

    @PersistenceContext
    private EntityManager em;

    // 태그 저장
    public void save(PostTag postTag) {
        em.persist(postTag);
    }

    // ID로 태그 삭제
    public void deleteById(Long id) {
        PostTag tag = em.find(PostTag.class, id);
        if (tag != null) {
            em.remove(tag);
        }
    }

    // 게시글 번호로 소속 태그 전체 조회 (JPQL)
    public List<PostTag> findByPostNo(Long postNo) {
        return em.createQuery("select pt from PostTag pt where pt.post.no = :postNo", PostTag.class)
                .setParameter("postNo", postNo)
                .getResultList();
    }

    // 게시글 번호로 소속 태그 전체 삭제 (벌크 연산)
    public void deleteByPostNo(Long postNo) {
        em.createQuery("delete from PostTag pt where pt.post.no = :postNo")
                .setParameter("postNo", postNo)
                .executeUpdate();
    }
}
