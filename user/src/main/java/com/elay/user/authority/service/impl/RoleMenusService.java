package com.elay.user.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elay.user.authority.service.IRoleMenusService;
import com.elay.user.authority.entity.RoleMenus;
import com.elay.user.authority.mapper.RoleMenusMapper;
import com.elay.user.authority.request.role.UpdRoleMenuReq;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<RoleMenus> listByRoleId(Integer roleId) {
        QueryWrapper<RoleMenus> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);
        return baseMapper.selectList(wrapper);
    }

    @Transactional
    @Override
    public boolean updMenu(UpdRoleMenuReq params) {
        //全量更新
        //先删除已有的
        QueryWrapper<RoleMenus> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",params.getRoleId());
        baseMapper.delete(wrapper);
        List<RoleMenus> rms = new ArrayList<>();
        //遍历新增
        params.getMenuIds().forEach(menuId->{
            RoleMenus roleMenus = new RoleMenus();
            roleMenus.setRoleId(params.getRoleId());
            roleMenus.setMenuId(menuId);
            rms.add(roleMenus);
        });
        return saveBatch(rms);
    }
}
