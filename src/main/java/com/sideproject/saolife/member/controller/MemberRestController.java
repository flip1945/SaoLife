package com.sideproject.saolife.member.controller;

import com.sideproject.saolife.Message;
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
    public ResponseEntity<Message> join(@RequestBody MemberJoinDTO memberJoinDTO) {
        Message message;
        try {
            memberService.join(memberJoinDTO);
        } catch (Exception e) {
            message = new Message(e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        message = new Message("OK, Member Join Success");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
