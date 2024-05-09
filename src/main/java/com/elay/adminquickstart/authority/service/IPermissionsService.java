package com.elay.adminquickstart.authority.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.adminquickstart.authority.request.permission.AddPermissionReq;
import com.elay.adminquickstart.authority.request.permission.UpdPermissionReq;
import com.elay.adminquickstart.authority.entity.Permissions;
import com.baomidou.mybatisplus.extension.service.IService;

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

    Page<Permissions> pageByParendId(Integer parentId, Page objectPage);

}
