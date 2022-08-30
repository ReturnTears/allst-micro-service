package com.allst.micro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Hutu
 * @since 2022-08-30 下午 09:14
 */
@Configuration
public class AllowedMethodConfig {
    @Autowired
    private TokenEndpoint tokenEndpoint;

    @PostConstruct
    public void reconfigure() {
        Set<HttpMethod> allowedMethods = new HashSet<>(Arrays.asList(HttpMethod.POST, HttpMethod.GET));
        tokenEndpoint.setAllowedRequestMethods(allowedMethods);
    }
}
