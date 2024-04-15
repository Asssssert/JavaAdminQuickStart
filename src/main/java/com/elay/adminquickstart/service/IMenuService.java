package com.elay.adminquickstart.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.adminquickstart.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.elay.adminquickstart.request.menu.AddMenuReq;
import com.elay.adminquickstart.request.menu.UpdMenuReq;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
public interface IMenuService extends IService<Menu> {

    boolean add(AddMenuReq params);

    boolean upd(UpdMenuReq params);

    Page<Menu> pageByParentId(Integer parentId, Integer page, Integer size);
}
