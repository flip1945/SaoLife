package com.sideproject.saolife.member.controller;

import com.sideproject.saolife.member.domain.Member;
import com.sideproject.saolife.member.domain.MemberJoinDTO;
import com.sideproject.saolife.member.domain.MemberUpdateDTO;
import com.sideproject.saolife.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("join")
    public String joinMemberPage() {
        return "member/joinForm";
    }

    @PostMapping(value = "join")
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

    @GetMapping(value = "members")
    public String members(Model model) {
        List<Member> members = memberService.findAll();

        model.addAttribute("members", members);
        return "member/members";
    }

    @PostMapping(value = "update")
    public String updateMember(MemberUpdateDTO memberUpdateDTO) {
        Member member = memberService.fineByEmail(memberUpdateDTO.getEmail()).orElseGet(null);

        if (member == null || !memberUpdateDTO.checkPassword(member)) {
            return "";
        }

        memberService.updateMember(memberUpdateDTO.getEmail(), memberUpdateDTO.getUpdatePassword());
        return "index";
    }
}
