package com.elay.adminquickstart.authority.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单权限关联表
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_menus")
public class RoleMenus implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer roleId;

    private Integer menuId;


}
