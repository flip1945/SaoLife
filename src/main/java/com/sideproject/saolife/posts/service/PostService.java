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

    @Transactional
    public Long save(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    @Transactional
    public void updatePost(Long postId, String title, String content) {
        Post findPost = postRepository.findOne(postId).get();

        findPost.updatePost(title, content);
    }

    public Optional<Post> findOne(Long id) {
        return postRepository.findOne(id);
    }
}
