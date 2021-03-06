package com.sideproject.saolife.member.service;

import com.sideproject.saolife.Exception.EmailNotFoundException;
import com.sideproject.saolife.Exception.PasswordNotMatchException;
import com.sideproject.saolife.member.domain.Member;
import com.sideproject.saolife.member.domain.MemberJoinDTO;
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
    public Long join(MemberJoinDTO memberJoinDTO) {
        validatePasswordCheck(memberJoinDTO);

        Member member = memberJoinDTO.toEntity();

        validateDuplicateMemberEmail(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validatePasswordCheck(MemberJoinDTO memberJoinDTO) {
        if (!memberJoinDTO.checkPassword()) {
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
        }
    }

    private void validateDuplicateMemberEmail(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());

        if (findMember.isPresent()) {
            throw new IllegalStateException("이미 등록된 이메일입니다.");
        }
    }

    @Transactional
    public void updateMember(String email, String password) {
        Member findMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("이메일을 찾을 수 없습니다."));

        findMember.updateMember(password);
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId)
                .orElseThrow(() -> new EmailNotFoundException("이메일을 찾을 수 없습니다."));
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("이메일을 찾을 수 없습니다."));
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
