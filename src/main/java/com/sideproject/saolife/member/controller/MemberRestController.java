package com.sideproject.saolife.member.controller;

import com.sideproject.saolife.member.domain.MemberJoinDTO;
import com.sideproject.saolife.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping("register")
    public ResponseEntity join(@RequestBody MemberJoinDTO memberJoinDTO) {
        System.out.println(memberJoinDTO);
        try {
            memberService.join(memberJoinDTO);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("test")
    public ResponseEntity join(@RequestBody String str) {
        System.out.println(str);
        return new ResponseEntity(HttpStatus.OK);
    }
}
