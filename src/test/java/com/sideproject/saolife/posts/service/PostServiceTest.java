package com.sideproject.saolife.posts.service;

import com.sideproject.saolife.member.domain.Member;
import com.sideproject.saolife.member.service.MemberService;
import com.sideproject.saolife.posts.domain.Post;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private EntityManager em;

    @Test
    public void 게시글_저장() {
        // given
        Member member = Member.builder()
                .email("coghdus1016@naver.com")
                .password("1234")
                .build();

        Post post = Post.builder()
                .title("hi")
                .content("hihihihi")
                .member(member)
                .build();

        // when
        memberService.join(member);
        Long postId = postService.save(post);

        // then
        Post findPost = postService.findOne(postId).get();
        assertThat(findPost.getTitle()).isEqualTo(post.getTitle());
        assertThat(findPost.getContent()).isEqualTo(post.getContent());
    }

    @Test
    public void 게시글_생성시간_확인() {
        // given
        LocalDateTime now = LocalDateTime.now();

        Member member = Member.builder()
                .email("coghdus1016@naver.com")
                .password("1234")
                .build();

        Post post = Post.builder()
                .title("hi")
                .content("hihihihi")
                .member(member)
                .build();

        memberService.join(member);
        Long postId = postService.save(post);

        // when
        Post findPost = postService.findOne(postId).get();

        // then
        Assertions.assertTrue(findPost.getCreatedDate().isAfter(now));
    }

    @Test
    public void 게시글_수정시간_확인() {
        // given
        Member member = Member.builder()
                .email("coghdus1016@naver.com")
                .password("1234")
                .build();

        Post post = Post.builder()
                .title("hi")
                .content("hihihihi")
                .member(member)
                .build();

        memberService.join(member);
        Long postId = postService.save(post);

        // when
        LocalDateTime now = LocalDateTime.now();
        Post findPost = postService.findOne(postId).get();

        postService.updatePost(findPost.getId(), "new title", "modified content");

        em.flush();

        // then
        Assertions.assertTrue(findPost.getModifiedDate().isAfter(now));
    }
}