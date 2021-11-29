package com.sideproject.saolife.posts.repository;

import com.sideproject.saolife.member.domain.Member;
import com.sideproject.saolife.posts.domain.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    public Optional<Post> findOne(Long id) {
        return Optional.ofNullable(em.find(Post.class, id));
    }

    public Optional<Post> findByEmail(Member member) {
        return em.createQuery("select p from Post p where p.member = :member", Post.class)
                .setParameter("member", member)
                .getResultList()
                .stream()
                .findAny();
    }
}
