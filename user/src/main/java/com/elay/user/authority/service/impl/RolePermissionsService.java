package com.elay.user.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elay.user.authority.service.IRolePermissionsService;
import com.elay.user.authority.entity.RolePermissions;
import com.elay.user.authority.mapper.RolePermissionsMapper;
import com.elay.user.authority.request.role.UpdRolePermissionReq;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色权限关联表
 服务实现类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Service
public class RolePermissionsService extends ServiceImpl<RolePermissionsMapper, RolePermissions> implements IRolePermissionsService {

    @Override
    public void delByPermissionId(Integer permissionId) {
        QueryWrapper<RolePermissions> wrapper = new QueryWrapper<>();
        wrapper.eq("permission_id",permissionId);
        baseMapper.delete(wrapper);
    }

    @Override
    public void delByRoleId(Integer roleId) {
        QueryWrapper<RolePermissions> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);
        baseMapper.delete(wrapper);
    }

    @Override
    public boolean updPermission(UpdRolePermissionReq params) {
        //先删除现有的权限
        QueryWrapper<RolePermissions> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",params.getRoleId());
        baseMapper.delete(wrapper);
        //添加新的的权限
        List<RolePermissions> rps = new ArrayList<>();
        for (Integer permissionId : params.getPermissionIds()) {
            RolePermissions permissions = new RolePermissions();
            permissions.setRoleId(params.getRoleId());
            permissions.setPermissionId(permissionId);
            rps.add(permissions);
        }
        return saveBatch(rps);
    }

    @Override
    public List<RolePermissions> listByRoleId(Integer roleId) {
        QueryWrapper<RolePermissions> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);
        return baseMapper.selectList(wrapper);
    }
}
