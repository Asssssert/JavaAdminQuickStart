package com.elay.adminquickstart.authority.response.menu;

import lombok.Data;

import java.util.List;

/**
 * @author LI
 * @since 2024/5/7
 */
@Data
public class AdminMenuResp {
    private Integer menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单路由
     */
    private String menuPath;

    /**
     * 父菜单ID
     */
    private Integer parentMenuId;

    /**
     * 菜单图标
     */
    private String menuIcon;

    private List<AdminMenuResp> children;

    private boolean hasChildren=false;
}
