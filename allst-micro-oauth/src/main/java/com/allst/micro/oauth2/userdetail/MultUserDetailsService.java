package com.allst.micro.oauth2.userdetail;

import com.allst.micro.dto.UserDTO;
import com.allst.micro.entity.Role;
import com.allst.micro.entity.UserJwt;
import com.allst.micro.mult.MultAuthentication;
import com.allst.micro.mult.MultAuthenticationContext;
import com.allst.micro.mult.authenticator.MultAuthenticator;
import com.allst.micro.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Hutu
 * @since 2022-08-30 下午 09:32
 */
@Slf4j
@Service("userDetailsService")
public class MultUserDetailsService implements UserDetailsService {

    private List<MultAuthenticator> authenticators;

    @Autowired
    private IRoleService roleService;

    @Autowired(required = false)
    public void setIntegrationAuthenticators(List<MultAuthenticator> authenticators) {
        this.authenticators = authenticators;
    }

    @Override
    public UserJwt loadUserByUsername(String username) throws UsernameNotFoundException {
        MultAuthentication multAuthentication = MultAuthenticationContext.get();
        //判断是否是集成登录
        if (multAuthentication == null) {
            multAuthentication = new MultAuthentication();
        }
        multAuthentication.setUsername(username);
        UserDTO user = this.authenticate(multAuthentication);

        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        return new UserJwt(
                user.getName(),
                user.getPassword(),
                !user.getDel(),
                user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),
                user.getAccountNonLocked(),
                this.obtainGrantedAuthorities(user), user.getId().toString());

    }

    /**
     * 获得登录者所有角色的权限集合.
     */
    protected Set<GrantedAuthority> obtainGrantedAuthorities(UserDTO user) {
        try {
            Set<Role> roles = roleService.queryUserRolesByUserId(user.getId().toString());
            log.info("user:{},roles:{}", user.getName(), roles);
            return roles.stream().map(role -> new SimpleGrantedAuthority(role.getCode())).collect(Collectors.toSet());
        } catch (Exception e) {
            e.printStackTrace();
            HashSet<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("NONE"));
            return grantedAuthorities;
        }
    }

    private UserDTO authenticate(MultAuthentication multAuthentication) {
        if (this.authenticators != null) {
            for (MultAuthenticator authenticator : authenticators) {
                if (authenticator.support(multAuthentication)) {
                    return authenticator.authenticate(multAuthentication);
                }
            }
        }
        return null;
    }
}

