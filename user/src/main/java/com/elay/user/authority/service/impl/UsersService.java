package com.elay.user.authority.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.user.authority.entity.*;
import com.elay.user.authority.service.IUsersService;
import com.elay.user.authority.mapper.UsersMapper;
import com.elay.user.authority.request.auth.LoginReq;
import com.elay.user.authority.request.auth.RegisterReq;
import com.elay.user.authority.request.user.AdminUpdUserReq;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elay.user.security.bean.UserRolesPerms;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private UserRolesService userRolesService;
    @Resource
    private RolesService rolesService;
    @Resource
    private PermissionsService permissionsService;

    @Resource
    private RolePermissionsService rolePermissionsService;


    @Override
    public Users login(LoginReq params) {
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper
                .eq("username", params.getUsername());
        Users getById = baseMapper.selectOne(wrapper);
        if (getById == null) {
            return null;
        }
        if (!getById.getPasswodHash().equals(SecureUtil.md5(params.getPassword() + getById.getSalt()))) {
            return null;
        }
        return getById;
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

    @Transactional
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

    @Override
    public Users findByUsername(String username) {
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username)
                .or()
                .eq("email", username);
        if (baseMapper.selectCount(wrapper) == 1) {
            return baseMapper.selectOne(wrapper);
        }
        return null;
    }

    @Override
    public List<String> getUserPermsByUsername(String username) {
        Users users = findByUsername(username);
//        UserRolesPerms perms = new UserRolesPerms();
//        perms.setUser(users);
        List<Roles> collect = userRolesService.listByUserId(users.getUserId())
                .stream()
                .map(UserRoles::getRoleId)
                .map(rolesService::getById)
                .collect(Collectors.toList());
        ArrayList<String> strings = new ArrayList<>();
        collect.forEach(role -> {
            List<String> list = rolePermissionsService.listByRoleId(role.getRoleId())
                    .stream()
                    .map(RolePermissions::getPermissionId)
                    .map(permissionsService::getById)
                    .map(Permissions::getPermissionCode)
                    .collect(Collectors.toList());
            strings.addAll(list);
        });
        return strings;
    }

    @Override
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

    @Transactional
    @Override
    public boolean delById(Integer userId) {
        return removeById(userId);
    }
}
