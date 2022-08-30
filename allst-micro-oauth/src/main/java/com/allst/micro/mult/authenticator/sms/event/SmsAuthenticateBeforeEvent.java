package com.allst.micro.mult.authenticator.sms.event;

import org.springframework.context.ApplicationEvent;

/**
 * 短信认证之前的事件，可以监听事件进行用户手机号自动注册
 *
 * @author Hutu
 * @since 2022-08-30 下午 10:00
 */
public class SmsAuthenticateBeforeEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public SmsAuthenticateBeforeEvent(Object source) {
        super(source);
    }
}
