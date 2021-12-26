package com.sideproject.saolife.auth.controller;

import com.sideproject.saolife.Exception.EmailNotFoundException;
import com.sideproject.saolife.Exception.PasswordNotMatchException;
import com.sideproject.saolife.auth.domain.LoginRequestDTO;
import com.sideproject.saolife.auth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("login")
    public String loginForm() {
        return "auth/loginForm";
    }

    @PostMapping("login")
    public String login(LoginRequestDTO loginRequestDTO, Model model, HttpSession session) {
        try {
            loginService.login(loginRequestDTO);
            session.setAttribute("userEmail", loginRequestDTO.getEmail());
        } catch (PasswordNotMatchException | EmailNotFoundException e) {
            model.addAttribute("msg", e.getMessage());
        }
        return "auth/login";
    }
}
