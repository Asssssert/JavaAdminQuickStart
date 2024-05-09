package com.elay.user.security.bean;

import com.elay.user.authority.entity.Permissions;
import com.elay.user.authority.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author LI
 * @since 2024/5/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IUserDetails implements UserDetails {
    private Users user;
    private UserRolesPerms userRolesPerms;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Permissions> permissionDtos = userRolesPerms.getPermissionsList();
        ArrayList<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Permissions dto : permissionDtos) {
            list.add(new SimpleGrantedAuthority(dto.getPermissionCode()));
        }
        return list;
    }

    @Override
    public String getPassword() {
        return user.getPasswodHash();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getState() == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
