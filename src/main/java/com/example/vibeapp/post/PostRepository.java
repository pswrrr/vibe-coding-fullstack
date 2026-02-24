package com.example.vibeapp.post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    @PersistenceContext
    private EntityManager em;

    // 모든 게시글 조회 (JPQL 사용)
    public List<Post> findAll() {
        return em.createQuery("select p from Post p order by p.no desc", Post.class)
                .getResultList();
    }

    // 번호로 게시글 상세 조회
    public Optional<Post> findByNo(Long no) {
        return Optional.ofNullable(em.find(Post.class, no));
    }

    // 게시글 저장 (영속화)
    public void save(Post post) {
        em.persist(post);
    }

    // 게시글 삭제
    public void deleteByNo(Long no) {
        Post post = em.find(Post.class, no);
        if (post != null) {
            em.remove(post);
        }
    }

    // 게시글 수정 (변경 감지 활용)
    // 별도의 update SQL 호출 대신, 트랜잭션 내에서 영속 상태 엔티티의 값을 변경하면 더티 체킹에 의해 반영됨
    public void update(Post post) {
        // 이미 영속성 컨텍스트에 포함된 post라면 필드 값 변경만으로 충분하지만,
        // DTO를 거쳐 새로 만들어진 엔티티일 경우 merge를 통해 반영
        em.merge(post);
    }

    // 전체 게시글 수 조회 (JPQL)
    public int count() {
        return em.createQuery("select count(p) from Post p", Long.class)
                .getSingleResult().intValue();
    }

    // 페이징 처리된 목록 조회
    public List<Post> findAllWithPaging(int offset, int limit) {
        return em.createQuery("select p from Post p order by p.no desc", Post.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
