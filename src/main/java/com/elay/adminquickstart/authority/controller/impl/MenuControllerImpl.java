package com.elay.adminquickstart.authority.controller.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.adminquickstart.authority.controller.MenuController;
import com.elay.adminquickstart.authority.response.Result;
import com.elay.adminquickstart.authority.response.menu.AdminMenuResp;
import com.elay.adminquickstart.authority.service.impl.MenuService;
import com.elay.adminquickstart.authority.service.impl.RoleMenusService;
import com.elay.adminquickstart.emus.ResponseStatus;
import com.elay.adminquickstart.authority.entity.Menu;
import com.elay.adminquickstart.authority.request.menu.AddMenuReq;
import com.elay.adminquickstart.authority.request.menu.UpdMenuReq;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LI
 * @since 2024/4/16
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600) // 允许所有来源的跨域请求，缓存时间为1小时
public class MenuControllerImpl implements MenuController {
    @Resource
    private MenuService menuService;
    @Resource
    private RoleMenusService roleMenusService;

    @Override
    public Result<Void> add(AddMenuReq params) {
        if (menuService.add(params)) {
            return Result.ok(ResponseStatus.SUCCESS);
        }
        return Result.err(ResponseStatus.MENU_EXIST_FOUND);
    }

    @Override
    public Result<Void> upd(UpdMenuReq params) {
        if (menuService.upd(params)) {
            return Result.ok(ResponseStatus.SUCCESS);
        }
        return Result.err(ResponseStatus.MENU_NOT_FOUND);
    }

    @Override
    public Result<Void> del(Integer menuId) {
        if (menuService.removeById(menuId)) {
            return Result.ok(ResponseStatus.SUCCESS);
        }
        //删除角色菜单关联表
        roleMenusService.delByMenuId(menuId);
        return Result.err(ResponseStatus.MENU_NOT_FOUND);
    }

    @Override
    public Result<Menu> get(Integer menuId) {
        Menu byId = menuService.getById(menuId);
        if (byId != null) {
            return Result.ok(ResponseStatus.SUCCESS, byId);
        }
        return Result.err(ResponseStatus.NOT_DATA, null);
    }

    @Override
    public Result<Page> page(Integer page, Integer size) {
        Page data = menuService.page(new Page<>(page, size));
        if (data != null) {
            List<Menu> records = data.getRecords();
            List<AdminMenuResp> resp = new ArrayList<>();
            for (Menu item : records) {
                if(item.getParentMenuId()==0){
                    AdminMenuResp ps = new AdminMenuResp();
                    BeanUtil.copyProperties(item, ps);
                    AdminMenuResp node = addNode(ps, item.getMenuId(), records);
                    resp.add(node);
                }
            }
            data.setRecords(resp);
            return Result.ok(ResponseStatus.SUCCESS, data);
        }
        return Result.err(ResponseStatus.NOT_DATA, null);

    }

    @Override
    public Result<Page<Menu>> pageByParentId(Integer parentId, Integer page, Integer size) {
        Page<Menu> data = menuService.pageByParentId(parentId, page, size);
        if (data != null) {
            return Result.ok(ResponseStatus.SUCCESS, data);
        }
        return Result.err(ResponseStatus.NOT_DATA, null);
    }

    private AdminMenuResp addNode(AdminMenuResp node, Integer parentId, List<Menu> list) {
        List<AdminMenuResp> childrens = new ArrayList<>();
        list.forEach(item -> {
            if (item.getParentMenuId().equals(parentId)) {
                AdminMenuResp resp = new AdminMenuResp();
                BeanUtil.copyProperties(item, resp);
                childrens.add(resp);
            }
        });
        if (childrens.size() > 0) {
            node.setHasChildren(true);
        }
        node.setChildren(childrens);
        return node;
    }
}
