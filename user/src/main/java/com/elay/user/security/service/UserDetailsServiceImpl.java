package com.elay.user.security.service;

import com.elay.user.authority.entity.Users;
import com.elay.user.authority.service.impl.UsersService;
import com.elay.user.emus.ResponseStatus;
import com.elay.user.security.bean.IUserDetails;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author LI
 * @since 2024/5/9
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UsersService usersService;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(ResponseStatus.LOGIN_FAIL.getMsg());
        }
        if (user.getState() != 0) {
            throw new RuntimeException(ResponseStatus.USER_STATE_FAIL.getMsg());
        }
        return new IUserDetails(user, usersService.getUserPermsByUsername(username));
    }
}
