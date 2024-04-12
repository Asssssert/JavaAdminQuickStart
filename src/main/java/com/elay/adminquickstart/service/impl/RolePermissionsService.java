package com.elay.adminquickstart.service.impl;

import com.elay.adminquickstart.entity.RolePermissions;
import com.elay.adminquickstart.mapper.RolePermissionsMapper;
import com.elay.adminquickstart.service.IRolePermissionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限关联表
 服务实现类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Service
public class RolePermissionsService extends ServiceImpl<RolePermissionsMapper, RolePermissions> implements IRolePermissionsService {

}
