package com.elay.adminquickstart.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elay.adminquickstart.entity.Users;
import com.elay.adminquickstart.mapper.UsersMapper;
import com.elay.adminquickstart.request.LoginReq;
import com.elay.adminquickstart.request.RegisterReq;
import com.elay.adminquickstart.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表
 * 服务实现类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Service
public class UsersService extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Override
    public boolean login(LoginReq params) {
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper
                .eq("username", params.getUsername());
        Users getById = baseMapper.selectOne(wrapper);
        if (getById == null) {
            return false;
        }
        if (!getById.getPasswodHash().equals(SecureUtil.md5(params.getPassword() + getById.getSalt()))) {
            return false;
        }
        return true;
    }

    public boolean register(RegisterReq params) {
        if (baseMapper.selectCount(new QueryWrapper<Users>().eq("username", params.getUsername())) == 0) {
            Users users = new Users();
            users.setSalt(SecureUtil.md5(params.getUsername() + params.getPassword()));
            users.setUsername(params.getUsername());
            users.setEmail(params.getEmail());
            users.setPasswodHash(SecureUtil.md5(params.getPassword() + users.getSalt()));
            return baseMapper.insert(users) > 0;
        }
        return false;
    }
}
