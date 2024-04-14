package com.elay.adminquickstart.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * @author LI
 * @since 2024/4/14
 */
@Data
public class AdminUpdUserReq {

    @NotNull(message = "用户ID不能为空")
    private Integer userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
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
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    /**
     * 性别（0男，1女，2其他
     */
    private Byte gender;
}
