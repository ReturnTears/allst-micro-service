package com.allst.micro.mult.authenticator;

import com.allst.micro.dto.UserDTO;
import com.allst.micro.mult.MultAuthentication;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Hutu
 * @since 2022-08-30 下午 09:35
 */
public interface MultAuthenticator {
    /**
     * 处理集成认证
     */
    UserDTO authenticate(MultAuthentication multAuthentication);

    /**
     * 进行预处理
     */
    void prepare(MultAuthentication multAuthentication, HttpServletResponse response);

    /**
     * 判断是否支持集成认证类型
     */
    boolean support(MultAuthentication multAuthentication);

    /**
     * 认证结束后执行
     */
    void complete(MultAuthentication multAuthentication);

}
