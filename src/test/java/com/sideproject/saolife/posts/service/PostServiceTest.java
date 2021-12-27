package com.sideproject.saolife.posts.service;

import com.sideproject.saolife.member.domain.Member;
import com.sideproject.saolife.member.domain.MemberJoinDTO;
import com.sideproject.saolife.member.service.MemberService;
import com.sideproject.saolife.posts.domain.Post;
import com.sideproject.saolife.posts.domain.PostRequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private MemberService memberService;

    @Test
    public void 게시글_저장() {
        // given
        MemberJoinDTO memberJoinDTO = new MemberJoinDTO("testEmail123@naver.com", "1234", "1234");
        memberService.join(memberJoinDTO);

        Member findMember = memberService.findByEmail("testEmail123@naver.com");

        PostRequestDTO postRequestDTO = new PostRequestDTO("hi", "hihihi", "testEmail123@naver.com");

        // when
        Long postId = postService.registerPost(postRequestDTO, findMember.getEmail());

        // then
        Post findPost = postService.findOne(postId).get();
        assertThat(findPost.getTitle()).isEqualTo(postRequestDTO.getTitle());
        assertThat(findPost.getContent()).isEqualTo(postRequestDTO.getContent());
    }
}