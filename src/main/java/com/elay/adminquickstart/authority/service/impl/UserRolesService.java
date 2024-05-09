package com.elay.adminquickstart.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elay.adminquickstart.authority.service.IUserRolesService;
import com.elay.adminquickstart.authority.entity.UserRoles;
import com.elay.adminquickstart.authority.mapper.UserRolesMapper;
import com.elay.adminquickstart.authority.request.user.UpdUserRoleReq;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        wrapper.eq("role_id", roleId);
        baseMapper.delete(wrapper);
    }

    @Override
    public void delByUserId(Integer userId) {
        QueryWrapper<UserRoles> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        baseMapper.delete(wrapper);
    }

    @Override
    public boolean updRoleIds(UpdUserRoleReq params) {
        //全量更新
        //先删除已有的
        delByUserId(params.getUserId());

        List<UserRoles> urs = new ArrayList<>();
        params.getRoleIds().forEach(roleId -> {
            UserRoles ur = new UserRoles();
            ur.setRoleId(roleId);
            ur.setUserId(params.getUserId());
            urs.add(ur);
        });
        return saveBatch(urs);
    }

    @Override
    public List<UserRoles> listByUserId(Integer userId) {
        QueryWrapper<UserRoles> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return baseMapper.selectList(wrapper);
    }
}
