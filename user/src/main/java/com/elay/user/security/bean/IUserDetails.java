package com.elay.user.security.bean;

import com.elay.user.authority.entity.Permissions;
import com.elay.user.authority.entity.Users;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LI
 * @since 2024/5/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"password","username","enabled","accountNonExpired", "accountNonLocked", "credentialsNonExpired", "authorities"})
public class IUserDetails implements UserDetails, Serializable {
    private Users user;
    private List<String> permList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
//        return null;
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
