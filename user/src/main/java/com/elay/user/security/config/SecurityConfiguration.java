package com.elay.user.security.config;

import com.elay.infra.constant.PermConstants;
import com.elay.user.authority.entity.Permissions;
import com.elay.user.authority.service.impl.PermissionsService;
import com.elay.user.security.filter.JwtAuthorizationFilter;
import com.elay.user.security.handler.IAccessDeniedHandler;
import com.elay.user.security.handler.IAuthenticationEntryPoint;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * @author LI
 * @since 2024/5/9
 */
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    @Resource
    private JwtAuthorizationFilter jwtAuthorizationFilter;
    @Resource
    private IAccessDeniedHandler accessDeniedHandler;
    @Resource
    private IAuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private PermissionsService permissionsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new IPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        List<Permissions> permsList = permissionsService.getPermissionParentId(PermConstants.PERMISSION_TYPE);
        //动态添加权限
        for (Permissions permissions : permsList) {
            System.out.println("地址:" + permissions.getPermissionApi() + "---权限:" + permissions.getPermissionCode());
            //anon的都通过
            if (permissions.getPermissionCode().equals("anon")) {
                http.authorizeRequests().requestMatchers(permissions.getPermissionApi()).permitAll();
            } else {
                http.authorizeRequests().requestMatchers(permissions.getPermissionApi()).hasAuthority(permissions.getPermissionCode());
            }
        }
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(handler -> handler
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
