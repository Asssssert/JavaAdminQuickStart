package com.elay.user.authority.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LI
 * @since 2024/5/9
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResp {
    private UserLoginResp user;
    private String token;
    private String expToken;
}
