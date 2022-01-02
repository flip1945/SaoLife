package com.sideproject.saolife.posts.controller;

import com.sideproject.saolife.Message;
import com.sideproject.saolife.posts.domain.PostRequestDTO;
import com.sideproject.saolife.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;

    @PostMapping("poster")
    public ResponseEntity<Message> poster(@RequestBody PostRequestDTO postRequestDTO, HttpSession session) {
        Message message;
        try {
            postService.registerPost(postRequestDTO);
        } catch (Exception e) {
            message = new Message(e.getMessage());
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        message = new Message("OK, Member Join Success");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
