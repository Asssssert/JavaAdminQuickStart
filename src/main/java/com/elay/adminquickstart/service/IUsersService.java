package com.elay.adminquickstart.service;

import com.elay.adminquickstart.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elay.adminquickstart.request.LoginReq;

/**
 * <p>
 * 用户表
 服务类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
public interface IUsersService extends IService<Users> {

    boolean login(LoginReq params);
}
