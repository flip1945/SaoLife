package com.sideproject.saolife.auth.controller;

import com.sideproject.saolife.Message;
import com.sideproject.saolife.auth.domain.LoginRequestDTO;
import com.sideproject.saolife.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class LoginRestController {

    private final LoginService loginService;

    @PostMapping("signIn")
    public ResponseEntity<Message> signIn(@RequestBody LoginRequestDTO loginRequestDTO, HttpSession session) {
        Message message;

        try {
            loginService.login(loginRequestDTO);
            session.setAttribute("userEmail", loginRequestDTO.getEmail());
        } catch (Exception e) {
            message = new Message(e.getMessage());
            return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
        }

        message = new Message("OK, Login Success");
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }
}
