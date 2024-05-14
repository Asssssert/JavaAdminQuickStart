package com.elay.user.authority.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elay.user.authority.service.IRolesService;
import com.elay.user.authority.entity.Roles;
import com.elay.user.authority.mapper.RolesMapper;
import com.elay.user.authority.request.role.AddRoleReq;
import com.elay.user.authority.request.role.UpdRoleReq;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 身份表
 * 服务实现类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Service
public class RolesService extends ServiceImpl<RolesMapper, Roles> implements IRolesService {

    @Transactional
    @Override
    public boolean add(AddRoleReq params) {
        //存在相同的身份
        if (existByRoleName(params.getRoleName())) {
            return false;
        }
        Roles roles = new Roles();
        BeanUtil.copyProperties(params, roles);
        return this.save(roles);
    }

    @Transactional
    @Override
    public boolean upd(UpdRoleReq params) {
        Roles roles = this.getById(params.getRoleId());
        //存在相同的身份
        if (existByRoleName(params.getRoleName())) {
            return false;
        }
        if (roles != null) {
            BeanUtil.copyProperties(params, roles);
            return this.updateById(roles);
        }
        return false;
    }

    @Transactional
    @Override
    public boolean delById(Integer roleId) {
        return removeById(roleId);
    }


    private boolean existByRoleName(String roleName) {
        QueryWrapper<Roles> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name", roleName);
        return this.count(wrapper) > 0 ? true : false;
    }
}
