package com.sideproject.saolife.member.controller;

import com.sideproject.saolife.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/signin")
    void member(String id, String password, String passwordCheck) {
        if ()

        memberService.join();

        return ;
    }
}
