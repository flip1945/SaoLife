package com.sideproject.saolife.member.service;

import com.sideproject.saolife.member.domain.Member;
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
        Member member = Member.builder()
                .email("coghdus1016@naver.com")
                .password("1234")
                .build();

        // when
        Long memberId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(memberId).get();
        assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(findMember.getPassword()).isEqualTo(member.getPassword());
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_가입_예외() {
        // given
        Member member1 = Member.builder()
                .email("coghdus1016@naver.com")
                .password("1234")
                .build();

        Member member2 = Member.builder()
                .email("coghdus1016@naver.com")
                .password("4567")
                .build();

        // when
        memberService.join(member1);
        memberService.join(member2);

        // then
        fail("예외가 발생해야 한다.");
    }

    @Test
    public void 이메일로_회원_찾기() {
        // given
        Member member = Member.builder()
                .email("coghdus1016@naver.com")
                .password("5678")
                .build();

        memberService.join(member);

        // when
        Member findMember = memberService.fineByEmail("coghdus1016@naver.com").get();

        // then
        assertThat(findMember.getPassword()).isEqualTo(member.getPassword());
    }

    @Test
    public void 모든_회원_찾기() {
        // given
        Member member1 = Member.builder()
                .email("a1@naver.com")
                .password("123")
                .build();

        Member member2 = Member.builder()
                .email("a2@naver.com")
                .password("123")
                .build();

        // when
        memberService.join(member1);
        memberService.join(member2);

        // then
        assertThat(memberService.findAll().size()).isEqualTo(2);
    }
}