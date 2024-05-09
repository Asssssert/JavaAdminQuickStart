package com.elay.user.authority.service;

import com.elay.user.authority.entity.UserRoles;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elay.user.authority.request.user.UpdUserRoleReq;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
public interface IUserRolesService extends IService<UserRoles> {

    void delByRoleId(Integer roleId);

    void delByUserId(Integer userId);

    boolean updRoleIds(UpdUserRoleReq params);

    List<UserRoles> listByUserId(Integer userId);
}
