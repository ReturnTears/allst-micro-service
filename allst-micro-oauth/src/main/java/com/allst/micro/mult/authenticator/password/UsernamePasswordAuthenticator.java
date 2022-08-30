package com.allst.micro.mult.authenticator.password;

import com.allst.micro.dto.UserDTO;
import com.allst.micro.mult.MultAuthentication;
import com.allst.micro.mult.authenticator.AbstractPreparableMultAuthenticator;
import com.allst.micro.remote.UserRemoteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * 默认登录处理
 *
 * @author Hutu
 * @since 2022-08-30 下午 09:45
 */
@Primary
@Component
public class UsernamePasswordAuthenticator extends AbstractPreparableMultAuthenticator {

    @Autowired
    UserRemoteService userRemoteService;

    @Override
    public UserDTO authenticate(MultAuthentication multAuthentication) {
        return this.userRemoteService.getUserByPhone(multAuthentication.getUsername());
    }

    @Override
    public void prepare(MultAuthentication multAuthentication, HttpServletResponse response) {
        System.out.println("come in method UsernamePasswordAuthenticator.prepare ");
    }

    @Override
    public boolean support(MultAuthentication multAuthentication) {
        return StringUtils.isEmpty(multAuthentication.getAuthType());
    }
}
