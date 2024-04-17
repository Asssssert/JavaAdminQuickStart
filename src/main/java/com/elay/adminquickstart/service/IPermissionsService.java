package com.elay.adminquickstart.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.adminquickstart.entity.Permissions;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elay.adminquickstart.request.permission.AddPermissionReq;
import com.elay.adminquickstart.request.permission.UpdPermissionReq;

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
