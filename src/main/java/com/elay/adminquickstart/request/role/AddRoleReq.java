package com.elay.adminquickstart.request.role;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author LI
 * @since 2024/4/15
 */
@Data
public class AddRoleReq {

    /**
     * 身份名
     */
    @NotBlank(message = "身份名不能为空")
    private String roleName;

    private String roleDescription;
}
