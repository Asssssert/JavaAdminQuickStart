package com.elay.user.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elay.user.authority.entity.RoleMenus;
import com.elay.user.authority.request.role.UpdRoleMenuReq;

import java.util.List;

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

    List<RoleMenus> listByRoleId(Integer roleId);

    boolean updMenu(UpdRoleMenuReq params);
    
}
