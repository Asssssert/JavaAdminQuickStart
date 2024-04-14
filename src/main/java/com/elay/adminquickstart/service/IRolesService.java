package com.elay.adminquickstart.service;

import com.elay.adminquickstart.entity.Roles;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elay.adminquickstart.request.role.AddRoleReq;
import com.elay.adminquickstart.request.role.UpdRoleReq;

/**
 * <p>
 * 身份表
 服务类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
public interface IRolesService extends IService<Roles> {

    boolean add(AddRoleReq params);

    boolean upd(UpdRoleReq params);
}
