package com.elay.adminquickstart.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.adminquickstart.entity.Users;
import com.elay.adminquickstart.mapper.UsersMapper;
import com.elay.adminquickstart.request.auth.LoginReq;
import com.elay.adminquickstart.request.auth.RegisterReq;
import com.elay.adminquickstart.request.user.AdminUpdUserReq;
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

    @Override
    public Page<Users> searchByKey(String key, Integer page, Integer size) {
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.like("username", key)
                .or()
                .like("email", key);
        Page<Users> usersPage = baseMapper.selectPage(new Page<>(page, size), wrapper);
        return usersPage;
    }

    @Override
    public boolean upd(Integer userId, AdminUpdUserReq params) {
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        Users byId = baseMapper.selectById(userId);
        if (byId != null) {
            wrapper.eq("username", userId)
                    .or()
                    .eq("email", params.getEmail());
            if (baseMapper.selectCount(wrapper) > 1) {
                return false;
            }
            BeanUtil.copyProperties(params, byId);
            return this.updateById(byId);
        }
        return false;
    }

    public boolean register(RegisterReq params) {
        QueryWrapper<Users> wrapper = new QueryWrapper<Users>()
                .eq("username", params.getUsername())
                .or()
                .eq("email", params.getEmail());
        if (baseMapper.selectCount(wrapper) == 0) {
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
