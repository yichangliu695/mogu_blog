package com.moxi.mogublog.commons.config.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * SpringSecurity中的用户实体类
 *
 * @author LiuYiChang
 * @date 2023/8/14
 */

@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final String uid;
    private final String username;
    private final String password;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    /**
     * 返回分配给用户的角色列表
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    public String getUid() {
        return uid;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 账户是否激活
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * 账户是否未过期
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未锁定
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否未过期
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}

