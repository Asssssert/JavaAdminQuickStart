package com.elay.adminquickstart.response.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author LI
 * @since 2024/4/14
 */
@Data
public class AdminUserResp {
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
    private String email;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedAt;

    /**
     * 性别（0男，1女，2其他
     */
    private Byte gender;
}