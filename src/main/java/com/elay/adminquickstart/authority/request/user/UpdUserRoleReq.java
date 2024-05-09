package com.elay.adminquickstart.authority.request.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @author LI
 * @since 2024/5/8
 */
@Data
public class UpdUserRoleReq {
    @NotNull(message = "用户ID不能为空")
    private Integer userId;
    private List<Integer> roleIds;
}
