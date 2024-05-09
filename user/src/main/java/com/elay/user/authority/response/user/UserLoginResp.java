package com.elay.user.authority.response.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author LI
 * @since 2024/5/9
 */
@Data
public class UserLoginResp {
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 加密后的密码
     */
//    private String passwodHash;

    /**
     * 加密盐
     */
//    private String salt;

    /**
     * 邮件
     */
//    private String email;

    /**
     * 手机号
     */
//    private String phoneNumber;

    /**
     * 创建时间
     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Date createdAt;

    /**
     * 修改时间
     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Date updatedAt;

    /**
     * 性别（0男，1女，2其他
     */
    private Byte gender;

    /**
     * 状态（0正常，1禁用）
     */
//    private Byte state;
}
