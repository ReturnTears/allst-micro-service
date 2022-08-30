package com.allst.micro.mult.authenticator.sms.exception;

import com.allst.micro.exception.CustomOauthExceptionSerializer;
import com.allst.micro.mult.authenticator.sms.result.SmsCodeValidateResult;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Hutu
 * @since 2022-08-30 下午 10:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class SmsValidateException extends RuntimeException{

    private SmsCodeValidateResult result;

    public SmsValidateException(SmsCodeValidateResult result) {
        this.result = result;
    }
}
