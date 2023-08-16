package com.dev.ForecastApiTestJarV2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dev.ForecastApiTestJarV2.constant.JwtTokenProvider;
import com.dev.ForecastApiTestJarV2.filter.JwtExceptionFilter;
import com.dev.ForecastApiTestJarV2.filter.JwtFilter;
import com.dev.ForecastApiTestJarV2.handler.JwtAuthenticationEntryPoint;
import com.dev.ForecastApiTestJarV2.service.member.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final CorsConfig corsConfig;
    private final JwtAuthenticationEntryPoint entryPoint;
    private final RedisTemplate<String, String> redisTemplate;
    private final String[] allowedUrls = {
    		"/api/member/signup", 
    		"/api/member/signin",
    		"/api/member/register"
    		};
    private final String[] notAllowedUrls = {
    		"/api/member/test", 
    		};
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .authenticationProvider(daoAuthenticationProvider())
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(allowedUrls).permitAll()
                .antMatchers(notAllowedUrls).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(corsConfig.corsFilter())
                .addFilterBefore(
                        new JwtFilter(jwtTokenProvider, redisTemplate),
                        UsernamePasswordAuthenticationFilter.class
                ).addFilterBefore(new JwtExceptionFilter(), JwtFilter.class);
                
                
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }
}