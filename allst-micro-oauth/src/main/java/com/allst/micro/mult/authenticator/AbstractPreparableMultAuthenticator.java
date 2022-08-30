package com.allst.micro.mult.authenticator;

import com.allst.micro.dto.UserDTO;
import com.allst.micro.mult.MultAuthentication;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Hutu
 * @since 2022-08-30 下午 09:38
 */
public abstract class AbstractPreparableMultAuthenticator implements MultAuthenticator {
    @Override
    public abstract UserDTO authenticate(MultAuthentication multAuthentication);

    @Override
    public abstract void prepare(MultAuthentication multAuthentication, HttpServletResponse response);

    @Override
    public abstract boolean support(MultAuthentication multAuthentication);

    @Override
    public void complete(MultAuthentication multAuthentication) {
        System.out.println("-- method: AbstractPreparableMultAuthenticator : 认证已结束...");
    }
}
