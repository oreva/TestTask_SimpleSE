package com.conductor.testtask.searchengine.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String INTERNAL_API_ROLE = "INTERNAL_API";

    @Value("${api.access.username}")
    private String user;

    @Value("${api.access.password}")
    private String pass;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(STATELESS);

        http.csrf().disable();

        http.anonymous();
        http.servletApi()
            .and()
            .headers().cacheControl();

        http.authorizeRequests()
            .antMatchers("/api/**").hasRole(INTERNAL_API_ROLE)
            .antMatchers("/rest/**").hasRole(INTERNAL_API_ROLE)
            .anyRequest().denyAll()
            .and()
            .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*PasswordEncoder passwordEncoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPwd = passwordEncoder.encode(pass);*/

        auth.inMemoryAuthentication()
            .withUser(user)
            .password("{noop}" + pass)
            .roles(INTERNAL_API_ROLE);
    }
}
