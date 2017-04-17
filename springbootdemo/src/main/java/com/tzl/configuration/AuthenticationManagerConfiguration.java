package com.tzl.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
public class AuthenticationManagerConfiguration extends
        GlobalAuthenticationConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select user_name username, password,1 from user where user_name = ?")
                .authoritiesByUsernameQuery("select username,authority from authorities where username = ?");
    }
}
