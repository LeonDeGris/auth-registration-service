package com.bmstu.flowrence.configuration;

import com.bmstu.flowrence.auth.jwt.JwtConfigurer;
import com.bmstu.flowrence.auth.jwt.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenService jwtTokenProvider;

    private static final String API_ENDPOINT = "/api/**";
    private static final String LOGIN_ENDPOINT = "/auth/*";
    private static final String INDEX_ENDPOINT = "/index/**";
    private static final String ERROR_ENDPOINT = "/error";

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.GET, "/**")
                .antMatchers(HttpMethod.POST, LOGIN_ENDPOINT)
                .antMatchers(ERROR_ENDPOINT);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT, INDEX_ENDPOINT, ERROR_ENDPOINT).permitAll()
                .antMatchers(HttpMethod.POST, API_ENDPOINT).permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
