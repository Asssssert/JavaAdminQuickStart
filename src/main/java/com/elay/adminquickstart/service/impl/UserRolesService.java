package com.elay.adminquickstart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elay.adminquickstart.entity.RoleMenus;
import com.elay.adminquickstart.entity.UserRoles;
import com.elay.adminquickstart.mapper.UserRolesMapper;
import com.elay.adminquickstart.service.IUserRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Service
public class UserRolesService extends ServiceImpl<UserRolesMapper, UserRoles> implements IUserRolesService {
    @Override
    public void delByRoleId(Integer roleId) {
        QueryWrapper<UserRoles> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);
        baseMapper.delete(wrapper);
    }

    @Override
    public void delByUserId(Integer userId) {
        QueryWrapper<UserRoles> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        baseMapper.delete(wrapper);
    }
}
