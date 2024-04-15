package com.elay.adminquickstart.controller.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.adminquickstart.controller.MenuController;
import com.elay.adminquickstart.emus.ResponseStatus;
import com.elay.adminquickstart.entity.Menu;
import com.elay.adminquickstart.request.menu.AddMenuReq;
import com.elay.adminquickstart.request.menu.UpdMenuReq;
import com.elay.adminquickstart.response.Result;
import com.elay.adminquickstart.service.impl.MenuService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LI
 * @since 2024/4/16
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600) // 允许所有来源的跨域请求，缓存时间为1小时
public class MenuControllerImpl implements MenuController {
    @Resource
    private MenuService menuService;

    @Override
    public Result<Void> add(AddMenuReq params) {
        if (menuService.add(params)) {
            return Result.ok(ResponseStatus.SUCCESS);
        }
        return Result.err(ResponseStatus.ID_NOT_FOUND);
    }

    @Override
    public Result<Void> upd(UpdMenuReq params) {
        if (menuService.upd(params)) {
            return Result.ok(ResponseStatus.SUCCESS);
        }
        return Result.err(ResponseStatus.ID_NOT_FOUND);
    }

    @Override
    public Result<Void> del(Integer userId) {
        if (menuService.removeById(userId)) {
            return Result.ok(ResponseStatus.SUCCESS);
        }
        return Result.err(ResponseStatus.ID_NOT_FOUND);
    }

    @Override
    public Result<Menu> get(Integer userId) {
        Menu byId = menuService.getById(userId);
        if (byId != null) {
            return Result.ok(ResponseStatus.SUCCESS, byId);
        }
        return Result.err(ResponseStatus.NOT_DATA, null);
    }

    @Override
    public Result<Page<Menu>> page(Integer page, Integer size) {
        Page<Menu> data = menuService.page(new Page<>(page, size));
        if (data != null) {
            return Result.ok(ResponseStatus.SUCCESS, data);
        }
        return Result.err(ResponseStatus.NOT_DATA, null);

    }

    @Override
    public Result<Page<Menu>> pageByParentId(Integer parentId, Integer page, Integer size) {
//        Page<Menu> data = menuService.pageByParentId(parentId, page, size);
        return null;
    }
}
