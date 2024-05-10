package com.elay.user.security.bean;

import com.elay.user.authority.entity.Permissions;
import com.elay.user.authority.entity.Roles;
import com.elay.user.authority.entity.Users;
import lombok.Data;

import java.util.List;

/**
 * @author LI
 * @since 2024/5/9
 */
@Data
public class UserRolesPerms {
    private List<Roles> rolesList;
    private List<Permissions> permissionsList;
}
