package com.elay.adminquickstart.authority.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表

 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_users")
public class Users implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 加密后的密码
     */
    private String passwodHash;

    /**
     * 加密盐
     */
    private String salt;

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

    /**
     * 状态（0正常，1禁用）
     */
    private Byte state;


}
