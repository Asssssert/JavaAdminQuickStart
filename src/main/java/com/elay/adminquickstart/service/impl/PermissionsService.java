package com.elay.adminquickstart.service.impl;

import com.elay.adminquickstart.entity.Permissions;
import com.elay.adminquickstart.mapper.PermissionsMapper;
import com.elay.adminquickstart.service.IPermissionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表
 服务实现类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Service
public class PermissionsService extends ServiceImpl<PermissionsMapper, Permissions> implements IPermissionsService {

}
