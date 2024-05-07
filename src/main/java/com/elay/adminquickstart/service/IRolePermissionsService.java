package com.elay.adminquickstart.service;

import com.elay.adminquickstart.entity.RolePermissions;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色权限关联表
 服务类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
public interface IRolePermissionsService extends IService<RolePermissions> {

    void delByPermissionId(Integer permissionId);

    void delByRoleId(Integer roleId);
}
