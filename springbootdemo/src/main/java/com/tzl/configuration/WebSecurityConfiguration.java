package com.tzl.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by hztangzhilong on 2016/12/16.
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.antMatcher("/**").authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
                .successForwardUrl("/findUser?userId=1")
                .failureUrl("/loginfailure.html");
    }
}
