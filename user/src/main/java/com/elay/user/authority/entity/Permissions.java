package com.elay.user.authority.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表

 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_permissions")
public class Permissions implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "permission_id", type = IdType.AUTO)
    private Integer permissionId;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 0是菜单
     */
    private Integer permissionParentId;
    /**
     * 0是菜单，1是接口
     */
    private Integer permissionType;

    /**
     * 权限代码
     */
    private String permissionCode;

    private String permissionDesc;


    /**
     * 请求方式
     */
    private String permissionMethod;

    /**
     * 请求地址
     */
    private String permissionApi;

}
