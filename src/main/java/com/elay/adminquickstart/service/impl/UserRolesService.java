package com.elay.adminquickstart.service.impl;

import com.elay.adminquickstart.entity.UserRoles;
import com.elay.adminquickstart.mapper.UserRolesMapper;
import com.elay.adminquickstart.service.IUserRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Service
public class UserRolesService extends ServiceImpl<UserRolesMapper, UserRoles> implements IUserRolesService {

}
