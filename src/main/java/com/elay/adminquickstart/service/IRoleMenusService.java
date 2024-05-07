package com.elay.adminquickstart.service;

import com.elay.adminquickstart.entity.RoleMenus;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜单权限关联表 服务类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
public interface IRoleMenusService extends IService<RoleMenus> {

    void delByRoleId(Integer roleId);

    void delByMenuId(Integer menuId);
}
