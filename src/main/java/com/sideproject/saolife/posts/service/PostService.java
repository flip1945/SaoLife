package com.sideproject.saolife.posts.service;

import com.sideproject.saolife.posts.domain.Post;
import com.sideproject.saolife.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = false)
    public Long save(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    public Optional<Post> findOne(Long id) {
        return postRepository.findOne(id);
    }
}
