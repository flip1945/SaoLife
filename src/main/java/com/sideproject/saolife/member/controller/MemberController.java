package com.sideproject.saolife.member.controller;

import com.sideproject.saolife.member.domain.Member;
import com.sideproject.saolife.member.domain.MemberJoinDTO;
import com.sideproject.saolife.member.domain.MemberUpdateDTO;
import com.sideproject.saolife.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("member")
    public String joinMemberPage() {
        return "member/joinForm";
    }

    @PostMapping("member")
    public String joinMember(MemberJoinDTO memberJoinDTO, Model model) {
        String type = "success";

        try {
            memberService.join(memberJoinDTO);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            if (e.getMessage().equals("비밀번호가 일치하지 않습니다.")) {
                type = "notMatch";
            } else {
                type = "duplicate";
            }
        }

        model.addAttribute("type", type);
        return "member/join";
    }

    @GetMapping("members")
    public String members(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "member/members";
    }

    @PatchMapping("member")
    public String updateMember(MemberUpdateDTO memberUpdateDTO) {
        Member member = memberService.findByEmail(memberUpdateDTO.getEmail());

        memberService.updateMember(memberUpdateDTO.getEmail(), memberUpdateDTO.getUpdatePassword());
        return "index";
    }
}
