package com.elay.adminquickstart.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elay.adminquickstart.entity.Roles;
import com.elay.adminquickstart.mapper.RolesMapper;
import com.elay.adminquickstart.request.role.AddRoleReq;
import com.elay.adminquickstart.request.role.UpdRoleReq;
import com.elay.adminquickstart.service.IRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean add(AddRoleReq params) {
        QueryWrapper<Roles> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name", params.getRoleName());
        if (this.count(wrapper) > 0) {
            return false;
        }
        Roles roles = new Roles();
        BeanUtil.copyProperties(params, roles);
        return this.save(roles);
    }

    @Override
    public boolean upd(UpdRoleReq params) {
        Roles roles = this.getById(params.getRoleId());
        if (roles != null) {
            BeanUtil.copyProperties(params, roles);
            return this.updateById(roles);
        }
        return false;
    }
}
