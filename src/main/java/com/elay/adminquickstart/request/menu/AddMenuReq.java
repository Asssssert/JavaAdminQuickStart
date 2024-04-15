package com.elay.adminquickstart.request.menu;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author LI
 * @since 2024/4/16
 */
@Data
public class AddMenuReq {

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;

    /**
     * 菜单路由
     */
    @NotBlank(message = "菜单路由不能为空")
    private String menuPath;

    /**
     * 父菜单ID
     */
    @NotBlank(message = "父菜单ID不能为空")
    private Integer parentMenuId;

    /**
     * 菜单图标
     */
    private String menuIcon;
}
