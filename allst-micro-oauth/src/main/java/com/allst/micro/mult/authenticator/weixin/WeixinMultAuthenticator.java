package com.allst.micro.mult.authenticator.weixin;

import com.allst.micro.dto.UserDTO;
import com.allst.micro.dto.WeixinDTO;
import com.allst.micro.mult.MultAuthentication;
import com.allst.micro.mult.authenticator.AbstractPreparableMultAuthenticator;
import com.allst.micro.remote.UserRemoteService;
import com.allst.micro.remote.WeixinRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * 微信开放平台登录
 *
 * @author Hutu
 * @since 2022-08-30 下午 09:49
 */
@Slf4j
@Component
public class WeixinMultAuthenticator extends AbstractPreparableMultAuthenticator {

    @Autowired
    UserRemoteService userRemoteService;

    @Autowired
    WeixinRemoteService weixinRemoteService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final static String WEIXIN_AUTH_TYPE = "weixin";

    @Override
    public UserDTO authenticate(MultAuthentication multAuthentication) {
        // 获取密码，实际值是openId
        String openId = multAuthentication.getAuthParameter("password");
        WeixinDTO weixinByOpenId = this.weixinRemoteService.getUserWeixinByOpenId(openId);
        if (null == weixinByOpenId) {
            return null;
        }

        // 通过用户id查询用户
        UserDTO userDTO = this.userRemoteService.getUserById(weixinByOpenId.getUserId());
        if (null == userDTO) {
            return null;
        }
        // 将密码设置为验证码
        userDTO.setPassword(passwordEncoder.encode(openId));
        return userDTO;
    }

    @Override
    public void prepare(MultAuthentication multAuthentication, HttpServletResponse response) {
        System.out.println("come in method WeixinMultAuthenticator.prepare ");
    }

    @Override
    public boolean support(MultAuthentication multAuthentication) {
        return WEIXIN_AUTH_TYPE.equals(multAuthentication.getAuthType());
    }
}
