package com.elay.adminquickstart.request.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author LI
 * @since 2024/4/15
 */
@Data
public class UpdRoleReq {
    @NotNull(message = "角色ID不能为空")
    private Integer roleId;

    /**
     * 身份名
     */
    @NotBlank(message = "身份不能为空")
    private String roleName;

    private String roleDescription;
}
