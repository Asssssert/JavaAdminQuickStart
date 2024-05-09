package com.elay.user.authority.service;

import com.elay.user.authority.entity.RolePermissions;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elay.user.authority.request.role.UpdRolePermissionReq;

import java.util.List;

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

    boolean updPermission(UpdRolePermissionReq params);

    List<RolePermissions> listByRoleId(Integer roleId);
}
