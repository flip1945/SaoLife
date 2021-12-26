package com.sideproject.saolife.auth.service;

import com.sideproject.saolife.Exception.EmailNotFoundException;
import com.sideproject.saolife.Exception.PasswordNotMatchException;
import com.sideproject.saolife.auth.domain.LoginRequestDTO;
import com.sideproject.saolife.member.domain.Member;
import com.sideproject.saolife.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberService memberService;

    public void login(LoginRequestDTO loginRequestDTO) {
        Member member = memberService.findByEmail(loginRequestDTO.getEmail());

        checkPassword(loginRequestDTO, member.getPassword());
    }

    private void checkPassword(LoginRequestDTO loginRequestDTO, String password) {
        if (!loginRequestDTO.checkPassword(password)) {
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
        }
    }
}
