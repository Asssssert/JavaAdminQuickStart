package com.elay.user.authority.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.user.authority.request.permission.AddPermissionReq;
import com.elay.user.authority.request.permission.UpdPermissionReq;
import com.elay.user.authority.entity.Permissions;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表
 服务类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
public interface IPermissionsService extends IService<Permissions> {

    boolean add(AddPermissionReq params);

    boolean upd(UpdPermissionReq params);

    Page<Permissions> pageByParentId(Integer parentId, Page objectPage);

    List<Permissions> getPermissionByType(int permissionType);

    List<Permissions> getPermissionParentId(int permissionType);
}
