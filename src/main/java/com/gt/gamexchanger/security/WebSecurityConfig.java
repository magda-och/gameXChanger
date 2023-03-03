package com.gt.gamexchanger.security;

import com.gt.gamexchanger.security.jwt.AuthEntryPointJwt;
import com.gt.gamexchanger.security.jwt.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AuthTokenFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    private final AuthEntryPointJwt authEntryPointJwt;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http    .cors().and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/user/auth/**")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                        .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                .authenticationProvider(authenticationProvider)
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(authEntryPointJwt);

        return http.build();
    }

}
