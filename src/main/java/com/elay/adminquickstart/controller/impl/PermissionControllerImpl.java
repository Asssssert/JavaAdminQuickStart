package com.elay.adminquickstart.controller.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.adminquickstart.controller.PermissionController;
import com.elay.adminquickstart.emus.ResponseStatus;
import com.elay.adminquickstart.entity.Permissions;
import com.elay.adminquickstart.entity.Roles;
import com.elay.adminquickstart.request.permission.AddPermissionReq;
import com.elay.adminquickstart.request.permission.UpdPermissionReq;
import com.elay.adminquickstart.response.Result;
import com.elay.adminquickstart.response.permission.AdminPermissionsResp;
import com.elay.adminquickstart.service.impl.PermissionsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author LI
 * @since 2024/4/15
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600) // 允许所有来源的跨域请求，缓存时间为1小时
public class PermissionControllerImpl implements PermissionController {
    @Resource
    private PermissionsService permissionsService;

    @Override
    public Result<Void> add(AddPermissionReq params) {
        if (!permissionsService.add(params)) {
            return Result.err(ResponseStatus.PERMISSION_EXIST_ERROR);
        }
        return Result.ok(ResponseStatus.SUCCESS);

    }

    @Override
    public Result<Void> upd(UpdPermissionReq params) {
        if (!permissionsService.upd(params)) {
            return Result.err(ResponseStatus.PERMISSION_NOT_EXIST_ERROR);
        }
        return Result.ok(ResponseStatus.SUCCESS);
    }

    @Override
    public Result<Void> del(Integer userId) {
        if (!permissionsService.removeById(userId)) {
            return Result.err(ResponseStatus.PERMISSION_NOT_EXIST_ERROR);
        }
        return Result.ok(ResponseStatus.SUCCESS);
    }

    @Override
    public Result<Permissions> get(Integer userId) {
        Permissions byId = permissionsService.getById(userId);
        if (byId == null) {
            return Result.err(ResponseStatus.PERMISSION_NOT_EXIST_ERROR, null);
        }
        return Result.ok(ResponseStatus.SUCCESS, byId);
    }

    @Override
    public Result<Page> page(Integer page, Integer size) {
        Page list = permissionsService.page(new Page<>(page, size));
        if (list != null) {
            List<Permissions> records = list.getRecords();
            List<AdminPermissionsResp> resp = new ArrayList<>();
            records.forEach(item -> {
                if (item.getPermissionParentId() == 0) {
                    AdminPermissionsResp ps = new AdminPermissionsResp();
                    BeanUtil.copyProperties(item, ps);
                    AdminPermissionsResp node = addNode(ps, item.getPermissionId(), records);
                    resp.add(node);
                }
            });
            list.setRecords(resp);
            return Result.ok(ResponseStatus.SUCCESS, list);
        }
        return Result.err(ResponseStatus.NOT_FOUND, null);
    }

    @Override
    public Result<Page> pageByParentId(Integer parentId, Integer page, Integer size) {
        Page list = permissionsService.pageByParendId(parentId, new Page<>(page, size));
        if (list != null) {

            return Result.ok(ResponseStatus.SUCCESS, list);
        }

        return Result.err(ResponseStatus.NOT_FOUND, null);
    }

    private AdminPermissionsResp addNode(AdminPermissionsResp node, Integer parentId, List<Permissions> permissionsList) {
        List<AdminPermissionsResp> childrens = new ArrayList<>();
        permissionsList.forEach(item -> {
            if (item.getPermissionParentId() == parentId) {
                AdminPermissionsResp resp = new AdminPermissionsResp();
                BeanUtil.copyProperties(item, resp);
                childrens.add(resp);
            }
        });
        if(childrens.size()>0){
            node.setHasChildren(true);
        }
        node.setChildren(childrens);
        return node;
    }
}
