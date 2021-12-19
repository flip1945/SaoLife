package com.sideproject.saolife.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class MemberJoinDTO {
    private String email;
    private String password;
    private String passwordCheck;

    public boolean checkPassword() {
        return password.equals(passwordCheck);
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .build();
    }
}
