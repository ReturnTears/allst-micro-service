package com.allst.micro.config;

import com.allst.micro.exception.CustomWebResponseExceptionTranslator;
import com.allst.micro.mult.MultAuthenticationFilter;
import com.allst.micro.oauth2.enhancer.CustomTokenEnhancer;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

/**
 * @author Hutu
 * @since 2022-08-30 ?????? 09:15
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    MultAuthenticationFilter multAuthenticationFilter;

    /**
     * jwt ??????????????????
     */
    @Value("${spring.security.oauth2.jwt.signingKey}")
    private String signingKey;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        // ?????????client????????????header???body???
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients()
//                .tokenEndpointAuthenticationFilters(null)
                .addTokenEndpointAuthenticationFilter(multAuthenticationFilter);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // ??????????????????????????????????????????????????????oauth_client_details???
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // ??????token???????????????????????????tokenServices?????????,?????????????????????????????????????????????TokenStore???TokenGranter???OAuth2RequestFactory
        endpoints.tokenStore(tokenStore())
                //?????????????????????refresh token??????????????????,true:reuse;false:no reuse.
                .reuseRefreshTokens(false)
                .authorizationCodeServices(authorizationCodeServices())
                .approvalStore(approvalStore())
                .exceptionTranslator(customExceptionTranslator())
                .tokenEnhancer(tokenEnhancerChain())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenGranter(tokenGranter(endpoints));
    }

    /**
     * ?????????OAuth2????????????
     */
    @Bean
    public WebResponseExceptionTranslator<OAuth2Exception> customExceptionTranslator() {
        return new CustomWebResponseExceptionTranslator();
    }

    /**
     * ???????????????????????????
     *
     * @return JdbcApprovalStore
     */
    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    /**
     * ?????????????????????????????????code
     */
    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        // ??????????????????????????????????????????jdbc?????????oauth_code???
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * token????????????
     */
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * ?????????token?????????
     */
    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new CustomTokenEnhancer(), accessTokenConverter()));
        return tokenEnhancerChain;
    }

    /**
     * jwt token???????????????
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        return converter;
    }

    /**
     * ??????????????????granter
     */
    public TokenGranter tokenGranter(final AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> granters = Lists.newArrayList(endpoints.getTokenGranter());
        return new CompositeTokenGranter(granters);
    }
}
