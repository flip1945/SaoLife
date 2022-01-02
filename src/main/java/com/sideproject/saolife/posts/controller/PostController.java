package com.sideproject.saolife.posts.controller;

import com.sideproject.saolife.Exception.PostNotFoundException;
import com.sideproject.saolife.posts.domain.PostRequestDTO;
import com.sideproject.saolife.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("post")
    public String post() {
        return "post/postForm";
    }

    @PostMapping("post")
    public String posting(PostRequestDTO postRequestDTO) {
        postService.registerPost(postRequestDTO);
        return "redirect:posts";
    }

    @GetMapping("post/{postNum}")
    public String postNum(@PathVariable Long postNum, Model model) {
        try {
            model.addAttribute("post", postService.findOne(postNum).orElseThrow(() -> new PostNotFoundException("찾을 수 없는 게시글입니다.")));
        } catch (Exception e) {
            return "index";
        }
        return "post/post";
    }

    @PatchMapping("post/{postNum}")
    public String updatePost(PostRequestDTO postRequestDTO, @PathVariable Long postNum) {
        postService.updatePost(postRequestDTO, postNum);
        return "post/posts";
    }

    @GetMapping("posts")
    public String posts(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "post/posts";
    }
}
