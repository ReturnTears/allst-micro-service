package com.allst.micro.exception;

import com.allst.micro.entity.vo.Result;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author Hutu
 * @since 2022-08-29 下午 10:34
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {

    private final Result<?> result;

    public CustomOauthException(OAuth2Exception oAuth2Exception) {
        super(oAuth2Exception.getSummary(), oAuth2Exception);
        this.result = Result.fail(AuthErrorType.valueOf(oAuth2Exception.getOAuth2ErrorCode().toUpperCase()), oAuth2Exception);
    }
}
