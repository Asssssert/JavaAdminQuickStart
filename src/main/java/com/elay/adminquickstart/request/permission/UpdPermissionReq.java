package com.elay.adminquickstart.request.permission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author LI
 * @since 2024/4/15
 */
@Data
public class UpdPermissionReq {
    @NotNull(message = "权限ID不能为空")
    private Integer permissionId;

    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称不能为空")
    private String permissionName;

    /**
     * 0是菜单，1是接口
     */
    @NotNull(message = "权限类型不能为空")
    private Byte permissionType;

    /**
     * 权限代码
     */
    @NotBlank(message = "权限代码不能为空")
    private String permissionCode;

    /**
     * 权限描述
     */
    private String permissionDesc;

    /**
     * 请求方式
     */
    private String permissionMethod;
}
