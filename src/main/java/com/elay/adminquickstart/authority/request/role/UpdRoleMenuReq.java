package com.elay.adminquickstart.authority.request.role;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @author LI
 * @since 2024/5/8
 */
@Data
public class UpdRoleMenuReq {
    //角色ID
    @NotNull(message = "角色ID不能为空")
    private Integer roleId;
    //菜单ID列表
    private List<Integer> menuIds;
}
