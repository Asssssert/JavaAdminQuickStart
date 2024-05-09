package com.elay.user.authority.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.user.authority.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elay.user.authority.request.auth.LoginReq;
import com.elay.user.authority.request.user.AdminUpdUserReq;

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

    Page<Users> searchByKey(String key, Integer page, Integer size);

    boolean upd(Integer userId, AdminUpdUserReq params);
}
