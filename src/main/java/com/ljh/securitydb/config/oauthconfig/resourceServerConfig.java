package com.ljh.securitydb.config.oauthconfig;

import com.ljh.securitydb.config.CustomAccessDecisionManager;
import com.ljh.securitydb.config.CustomFilterInvocationSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author LuoJiaHui
 * @date 2020/9/21 13:59
 * @description
 */
@Configuration
@EnableResourceServer
public class resourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private CustomAccessDecisionManager accessDecisionManager;
    @Autowired
    private CustomFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("res01").stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
                        object.setAccessDecisionManager(accessDecisionManager);
                        return object;
                    }
                })
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable();
    }
}
