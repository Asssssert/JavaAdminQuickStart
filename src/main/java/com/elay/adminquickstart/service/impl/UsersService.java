package com.elay.adminquickstart.service.impl;

import com.elay.adminquickstart.entity.Users;
import com.elay.adminquickstart.mapper.UsersMapper;
import com.elay.adminquickstart.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表
 服务实现类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Service
public class UsersService extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
