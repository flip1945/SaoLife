package com.sideproject.saolife.posts.controller;

import com.sideproject.saolife.posts.domain.PostRequestDTO;
import com.sideproject.saolife.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("post")
    public String post() {
        return "post/postForm";
    }

    @PostMapping("post")
    public String posting(PostRequestDTO postRequestDTO, HttpSession session) {
        System.out.println("userEmail : " + (String) session.getAttribute("userEmail"));
        System.out.println("post" + postRequestDTO);
        postService.registerPost(postRequestDTO, (String) session.getAttribute("userEmail"));
        return "index";
    }

    @PatchMapping("post/{postNum}")
    public String updatePost(PostRequestDTO postRequestDTO, @PathVariable Long postNum) {
        postService.updatePost(postRequestDTO, postNum);
        return "post/updatePost";
    }

    @GetMapping("posts")
    public String posts(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "post/posts";
    }
}
