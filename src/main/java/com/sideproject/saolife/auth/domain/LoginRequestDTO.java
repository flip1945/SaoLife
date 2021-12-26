package com.sideproject.saolife.auth.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    private String email;
    private String password;

    public boolean checkPassword(String passwordCheck) {
        return password.equals(passwordCheck);
    }
}
