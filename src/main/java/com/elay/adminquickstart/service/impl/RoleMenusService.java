package com.elay.adminquickstart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elay.adminquickstart.entity.RoleMenus;
import com.elay.adminquickstart.mapper.RoleMenusMapper;
import com.elay.adminquickstart.service.IRoleMenusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单权限关联表 服务实现类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Service
public class RoleMenusService extends ServiceImpl<RoleMenusMapper, RoleMenus> implements IRoleMenusService {

    @Override
    public void delByRoleId(Integer roleId) {
        QueryWrapper<RoleMenus> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);
        baseMapper.delete(wrapper);
    }

    @Override
    public void delByMenuId(Integer menuId) {
        QueryWrapper<RoleMenus> wrapper = new QueryWrapper<>();
        wrapper.eq("menu_id",menuId);
        baseMapper.delete(wrapper);
    }
}
