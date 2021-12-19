package com.sideproject.saolife.member.service;

import com.sideproject.saolife.member.domain.Member;
import com.sideproject.saolife.member.domain.MemberJoinDTO;
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
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void 회원_가입() {
        // given
        MemberJoinDTO memberJoinDTO = new MemberJoinDTO("testEmail@naver.com", "1234", "1234");

        // when
        Long memberId = memberService.join(memberJoinDTO);

        // then
        Member findMember = memberService.findOne(memberId).get();
        assertThat(findMember.getEmail()).isEqualTo(memberJoinDTO.getEmail());
        assertThat(findMember.getPassword()).isEqualTo(memberJoinDTO.getPassword());
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_가입_예외() {
        // given
        MemberJoinDTO memberJoinDTO1 = new MemberJoinDTO("testEmail@naver.com", "1234", "1234");
        MemberJoinDTO memberJoinDTO2 = new MemberJoinDTO("testEmail@naver.com", "4567", "4567");

        // when
        memberService.join(memberJoinDTO1);
        memberService.join(memberJoinDTO2);

        // then
        fail("예외가 발생해야 한다.");
    }

    @Test
    public void 이메일로_회원_찾기() {
        // given
        MemberJoinDTO memberJoinDTO = new MemberJoinDTO("testEmail@naver.com", "1234", "1234");

        memberService.join(memberJoinDTO);

        // when
        Member findMember = memberService.fineByEmail("testEmail@naver.com").get();

        // then
        assertThat(findMember.getPassword()).isEqualTo(memberJoinDTO.getPassword());
    }
}