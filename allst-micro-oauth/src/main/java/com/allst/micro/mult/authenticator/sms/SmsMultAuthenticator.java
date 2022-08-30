package com.allst.micro.mult.authenticator.sms;

import com.allst.micro.dto.UserDTO;
import com.allst.micro.exception.AuthErrorType;
import com.allst.micro.mult.MultAuthentication;
import com.allst.micro.mult.authenticator.AbstractPreparableMultAuthenticator;
import com.allst.micro.mult.authenticator.sms.event.SmsAuthenticateBeforeEvent;
import com.allst.micro.mult.authenticator.sms.event.SmsAuthenticateSuccessEvent;
import com.allst.micro.mult.authenticator.sms.exception.SmsValidateException;
import com.allst.micro.mult.authenticator.sms.result.SmsCodeValidateResult;
import com.allst.micro.mult.authenticator.sms.result.SmsValidateResultContext;
import com.allst.micro.remote.UserRemoteService;
import com.allst.micro.remote.VerificationCodeRemoteService;
import com.allst.micro.response.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * 短信验证码集成认证
 *
 * @author Hutu
 * @since 2022-08-30 下午 09:56
 */
@Slf4j
@Component
public class SmsMultAuthenticator extends AbstractPreparableMultAuthenticator implements ApplicationEventPublisherAware {

    @Autowired
    UserRemoteService userRemoteService;

    @Autowired
    VerificationCodeRemoteService verificationCodeRemoteService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private ApplicationEventPublisher applicationEventPublisher;

    private final static String SMS_AUTH_TYPE = "mobile";

    @Override
    public UserDTO authenticate(MultAuthentication multAuthentication) {
        SmsCodeValidateResult validateResult = SmsValidateResultContext.get();
        if (null != validateResult && !validateResult.isSuccess()) {
            AuthErrorType authErrorType = validateResult.getAuthErrorType();
            log.info("短信验证码错误,手机号:{}, 结果:{}", multAuthentication.getUsername(), authErrorType);
//            throw new OAuth2Exception(authErrorType.getMesg());
            return null;
        }

        //获取密码，实际值是验证码
        String password = multAuthentication.getAuthParameter("password");
        //获取用户名，实际值是手机号
        String username = multAuthentication.getUsername();
        //发布事件，可以监听事件进行自动注册用户
        this.applicationEventPublisher.publishEvent(new SmsAuthenticateBeforeEvent(multAuthentication));
        //通过手机号码查询用户
        UserDTO userDTO = this.userRemoteService.getUserByPhone(username);
        if (userDTO != null) {
            //将密码设置为验证码
            userDTO.setPassword(passwordEncoder.encode(password));
            //发布事件，可以监听事件进行消息通知
            this.applicationEventPublisher.publishEvent(new SmsAuthenticateSuccessEvent(multAuthentication));
        }
        return userDTO;
    }

    @Override
    public void prepare(MultAuthentication multAuthentication, HttpServletResponse response) {
        System.out.println("come in method SmsMultAuthenticator.prepare ");

        String smsCode = multAuthentication.getAuthParameter("password");
        String username = multAuthentication.getAuthParameter("username");
        ResponseDTO<?> result = verificationCodeRemoteService.checkCode(username, smsCode);
        if (null == result || !result.isSuccess()) {

            SmsCodeValidateResult validateResult = new SmsCodeValidateResult();
            validateResult.setSuccess(false);
            validateResult.setAuthErrorType(AuthErrorType.ERROR_VERIFY_CODE);

            throw new SmsValidateException(validateResult);
        }
    }

    @Override
    public boolean support(MultAuthentication multAuthentication) {
        return SMS_AUTH_TYPE.equals(multAuthentication.getAuthType());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
