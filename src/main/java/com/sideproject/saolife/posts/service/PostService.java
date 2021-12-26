package com.sideproject.saolife.posts.service;

import com.sideproject.saolife.Exception.NotLoginException;
import com.sideproject.saolife.member.domain.Member;
import com.sideproject.saolife.member.service.MemberService;
import com.sideproject.saolife.posts.domain.Post;
import com.sideproject.saolife.posts.domain.PostRequestDTO;
import com.sideproject.saolife.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;

    @Transactional
    public Long registerPost(PostRequestDTO postRequestDTO, String email) {
        checkLogin(email);

        Member member = memberService.findByEmail(postRequestDTO.getEmail());
        Post post = postRequestDTO.toPost(member);

        postRepository.save(post);
        return post.getId();
    }

    private void checkLogin(String email) {
        if (email == null || email.equals("")) {
            throw new NotLoginException("로그인 후에 이용해주십시오.");
        }
    }

    @Transactional
    public void updatePost(PostRequestDTO postRequestDTO, Long postNum) {
        Post findPost = postRepository.findOne(postNum).orElseThrow(() -> new IllegalStateException("이메일을 찾을 수 없습니다."));

        findPost.updatePost(postRequestDTO.getTitle(), postRequestDTO.getContent());
    }

    public Optional<Post> findOne(Long id) {
        return postRepository.findOne(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.delete(postId);
    }
}
