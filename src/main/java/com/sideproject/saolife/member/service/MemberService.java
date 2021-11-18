package com.sideproject.saolife.member.service;

import com.sideproject.saolife.member.domain.Member;
import com.sideproject.saolife.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMemberEmail(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMemberEmail(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());

        if (findMember.isPresent()) {
            throw new IllegalStateException("이미 등록된 이메일입니다.");
        }
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public Optional<Member> fineByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
