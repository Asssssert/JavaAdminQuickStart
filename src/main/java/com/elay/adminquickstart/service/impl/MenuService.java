package com.elay.adminquickstart.service.impl;

import com.elay.adminquickstart.entity.Menu;
import com.elay.adminquickstart.mapper.MenuMapper;
import com.elay.adminquickstart.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
