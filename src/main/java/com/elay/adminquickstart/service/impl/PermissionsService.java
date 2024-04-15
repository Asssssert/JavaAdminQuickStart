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
 服务实现类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Service
public class PermissionsService extends ServiceImpl<PermissionsMapper, Permissions> implements IPermissionsService {

    @Override
    public boolean add(AddPermissionReq params) {
        QueryWrapper<Permissions> wrapper = new QueryWrapper<>();
        wrapper.eq("permission_name", params.getPermissionName());
        if(this.count(wrapper)>0){
            return false;
        }
        Permissions permissions = new Permissions();
        BeanUtil.copyProperties(params, permissions);
        return this.save(permissions);
    }

    @Override
    public boolean upd(UpdPermissionReq params) {
        Permissions byId = getById(params.getPermissionId());
        if(byId!=null){
            BeanUtil.copyProperties(params, byId);
            return this.updateById(byId);
        }
        return false;
    }
}
