package com.example.cashdesk.security.config;

import com.example.cashdesk.security.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${security.api.header}")
    private String authHeaderName;

    @Value("${security.api.key}")
    private String apiKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authHeaderName, apiKey);

        http.csrf().disable()
                .cors().disable()
                .authorizeHttpRequests(registry ->
                        registry.requestMatchers(new AntPathRequestMatcher("/**")).authenticated())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);;

        return http.build();
    }
}
