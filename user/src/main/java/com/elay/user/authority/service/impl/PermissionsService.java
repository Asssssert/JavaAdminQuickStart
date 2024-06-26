package com.elay.user.authority.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.user.authority.request.permission.AddPermissionReq;
import com.elay.user.authority.request.permission.UpdPermissionReq;
import com.elay.user.authority.service.IPermissionsService;
import com.elay.user.authority.entity.Permissions;
import com.elay.user.authority.mapper.PermissionsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    @Override
    public boolean add(AddPermissionReq params) {
        if (exitByUsername(params.getPermissionName())) {
            return false;
        }
        Permissions permissions = new Permissions();
        BeanUtil.copyProperties(params, permissions);
        return this.save(permissions);
    }

    @Transactional
    @Override
    public boolean upd(UpdPermissionReq params) {
        Permissions byId = getById(params.getPermissionId());
        if (byId != null) {
            if (!exitByUsername(params.getPermissionName())) {
                return false;
            }
            BeanUtil.copyProperties(params, byId);
            return this.updateById(byId);
        }
        return false;
    }

    private boolean exitByUsername(String username) {
        QueryWrapper<Permissions> wrapper = new QueryWrapper<>();
        wrapper.eq("permission_name", username);
        return this.count(wrapper) > 0;
    }

    @Override
    public Page<Permissions> pageByParentId(Integer parentId, Page objectPage) {
        QueryWrapper<Permissions> wrapper = new QueryWrapper<>();
        wrapper.eq("permission_parent_id", parentId);
        return this.page(objectPage, wrapper);
    }

    @Override
    public List<Permissions> getPermissionByType(int permissionType) {
        QueryWrapper<Permissions> wrapper = new QueryWrapper<>();
        wrapper.eq("permission_type", permissionType);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<Permissions> getPermissionParentId(int permissionType) {
        QueryWrapper<Permissions> wrapper = new QueryWrapper<>();
        wrapper.ne("permission_parent_id", permissionType);
        return baseMapper.selectList(wrapper);
    }

    @Transactional
    @Override
    public boolean delById(Integer permissionId) {
        return removeById(permissionId);
    }
}
