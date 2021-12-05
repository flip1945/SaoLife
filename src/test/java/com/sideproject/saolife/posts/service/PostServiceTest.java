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
import java.util.List;

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
                .email("coghdus@naver.com")
                .password("1234")
                .build();

        Post post = Post.builder()
                .title("create")
                .content("create content")
                .member(member)
                .build();

        memberService.join(member);
        Long postId = postService.save(post);
        em.flush();

        // when
        Post findPost = postService.findOne(postId).get();

        System.out.println("now time : " + now);
        System.out.println("생성 시간 : " + findPost.getCreatedDate());
        System.out.println("수정 시간 : " + findPost.getModifiedDate());

        // then
        Assertions.assertTrue(findPost.getCreatedDate().isAfter(now));
    }

    @Test
    public void 게시글_수정시간_확인() {
        // given
        Member member = Member.builder()
                .email("coghdus1@naver.com")
                .password("1234")
                .build();

        Post post = Post.builder()
                .title("modify")
                .content("modify content")
                .member(member)
                .build();

        memberService.join(member);
        Long postId = postService.save(post);

        // when
        LocalDateTime now = LocalDateTime.now();
        Post findPost = postService.findOne(postId).get();

        postService.updatePost(findPost.getId(), "new title", "modified content");

        System.out.println("now time : " + now);
        System.out.println("생성 시간 : " + findPost.getCreatedDate());
        System.out.println("수정 시간 : " + findPost.getModifiedDate());

        em.flush();

        // then
        Assertions.assertTrue(findPost.getModifiedDate().isAfter(now));
    }

    @Test
    public void 다수의_게시글_저장() {
        // given
        Member member = Member.builder()
                .email("coghdus1016@naver.com")
                .password("1234")
                .build();

        Post post1 = Post.builder()
                .title("hi")
                .content("hihihihi")
                .member(member)
                .build();

        Post post2 = Post.builder()
                .title("hi")
                .content("hihihihi")
                .member(member)
                .build();

        Post post3 = Post.builder()
                .title("hi")
                .content("hihihihi")
                .member(member)
                .build();

        // when
        memberService.join(member);
        postService.save(post1);
        postService.save(post2);
        postService.save(post3);

        // then
        List<Post> findAllPosts = postService.findAll();
        assertThat(findAllPosts.size()).isEqualTo(3);
    }

    @Test
    public void 게시글_삭제() {
        // given
        Member member = Member.builder()
                .email("coghdus1016@naver.com")
                .password("1234")
                .build();

        Post post1 = Post.builder()
                .title("hi")
                .content("hihihihi")
                .member(member)
                .build();

        Post post2 = Post.builder()
                .title("hi")
                .content("hihihihi")
                .member(member)
                .build();

        Post post3 = Post.builder()
                .title("hi")
                .content("hihihihi")
                .member(member)
                .build();

        // when
        memberService.join(member);
        Long findId1 = postService.save(post1);
        Long findId2 = postService.save(post2);
        Long findId3 = postService.save(post3);

        // then
        postService.deletePost(findId2);
        postService.deletePost(findId3);

        List<Post> findAllPosts = postService.findAll();
        assertThat(findAllPosts.size()).isEqualTo(1);
    }
}