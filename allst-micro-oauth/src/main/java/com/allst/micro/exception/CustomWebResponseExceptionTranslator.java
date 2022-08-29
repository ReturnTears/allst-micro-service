package com.allst.micro.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;

/**
 * @author Hutu
 * @since 2022-08-29 下午 10:47
 */
@Slf4j
public class CustomWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {
    /**
     * @param e spring security内部异常
     * @return 经过处理的异常信息
     * @throws Exception 通用异常
     */
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        log.error("Grant Error : " + e);
        OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
        return ResponseEntity.status(HttpStatus.OK).body(new CustomOauthException(oAuth2Exception));
    }
}
