package com.allst.micro.mult.authenticator.sms.result;

import com.allst.micro.exception.AuthErrorType;
import lombok.Data;

/**
 * @author Hutu
 * @since 2022-08-30 下午 09:59
 */
@Data
public class SmsCodeValidateResult {
    private boolean success = true;
    private AuthErrorType authErrorType;
}
