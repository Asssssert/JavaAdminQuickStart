package com.elay.adminquickstart.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elay.adminquickstart.entity.Permissions;
import com.elay.adminquickstart.mapper.PermissionsMapper;
import com.elay.adminquickstart.request.permission.AddPermissionReq;
import com.elay.adminquickstart.request.permission.UpdPermissionReq;
import com.elay.adminquickstart.service.IPermissionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表
 * 服务实现类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Service
public class PermissionsService extends ServiceImpl<PermissionsMapper, Permissions> implements IPermissionsService {

    @Override
    public boolean add(AddPermissionReq params) {
        if (exitByUsername(params.getPermissionName())) {
            return false;
        }
        Permissions permissions = new Permissions();
        BeanUtil.copyProperties(params, permissions);
        return this.save(permissions);
    }

    @Override
    public boolean upd(UpdPermissionReq params) {
        Permissions byId = getById(params.getPermissionId());
        if (byId != null) {
            if (!exitByUsername(params.getPermissionName())) {
                return false;
            }
            return this.updateById(byId);
        }
        return false;
    }

    private boolean exitByUsername(String username) {
        QueryWrapper<Permissions> wrapper = new QueryWrapper<>();
        wrapper.eq("permission_name", username);
        if (this.count(wrapper) > 0) {
            return true;
        }
        return false;
    }
}
