package com.elay.user.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class IPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        System.out.println("rawPassword:" + rawPassword.toString());
//        System.out.println("encodedPassword:" + encodedPassword);
//        String pwd = SecureUtil.md5(rawPassword + users.getSalt());
//        System.out.println("pwd:"+pwd);
        return encodedPassword.equals(rawPassword);
    }
}
