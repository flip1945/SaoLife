package com.sideproject.saolife.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MemberUpdateDTO {
    private String email;
    private String prePassword;
    private String updatePassword;
    private String updatePasswordCheck;

    public boolean checkPassword(Member member) {
        if (!member.getPassword().equals(prePassword)) {
            return false;
        }

        return updatePassword.equals(updatePasswordCheck);
    }
}
