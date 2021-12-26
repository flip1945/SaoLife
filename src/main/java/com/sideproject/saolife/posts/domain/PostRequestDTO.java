package com.sideproject.saolife.posts.domain;

import com.sideproject.saolife.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PostRequestDTO {

    private String title;
    private String content;
    private String email;

    public Post toPost(Member member) {
        return Post.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
    }
}
