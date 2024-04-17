package com.elay.adminquickstart.response.permission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LI
 * @since 2024/4/17
 */
@Data
public class AdminPermissionsResp {
    private Integer permissionId;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 0是菜单，1是接口
     */
    private Integer permissionParentId;

    /**
     * 权限代码
     */
    private String permissionCode;

    /**
     * 权限描述
     */
    private String permissionDesc;

    /**
     * 请求方式
     */
    private String permissionMethod;
    /**
     * 请求地址
     */
    private String permissionApi;

    private List<AdminPermissionsResp> children;

    private boolean hasChildren=false;
}
