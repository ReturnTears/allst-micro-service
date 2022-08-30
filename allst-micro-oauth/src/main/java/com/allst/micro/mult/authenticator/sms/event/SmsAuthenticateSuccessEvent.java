package com.allst.micro.mult.authenticator.sms.event;

import org.springframework.context.ApplicationEvent;

/**
 * 短信认证成功事件
 *
 * @author Hutu
 * @since 2022-08-30 下午 10:01
 */
public class SmsAuthenticateSuccessEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public SmsAuthenticateSuccessEvent(Object source) {
        super(source);
    }
}
