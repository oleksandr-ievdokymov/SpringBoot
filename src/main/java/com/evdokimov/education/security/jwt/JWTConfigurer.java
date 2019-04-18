package com.evdokimov.education.security.jwt;

import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String SIGNING_KEY = "springSK";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final Integer TOKEN_PREFIX_LENGTH = 7;
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 10 * 60 * 60;
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS_RM = 12 * 120 * 120;

    private TokenProvider tokenProvider;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        JWTFilter customFilter = new JWTFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
